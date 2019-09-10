package com.youyu.cotenant.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonNull;
import com.qiniu.util.Json;
import com.youyu.cotenant.common.CotenantConstants;
import com.youyu.cotenant.common.GeneratorID;
import com.youyu.cotenant.entity.CotenantChatMsg;
import com.youyu.cotenant.entity.CotenantCommunication;
import com.youyu.cotenant.entity.CotenantCommunicationExample;
import com.youyu.cotenant.repository.CotenantChatMsgMapper;
import com.youyu.cotenant.repository.CotenantCommunicationMapper;
import com.youyu.cotenant.repository.CotenantUserInfoMapper;
import com.youyu.cotenant.repository.biz.CotenantChatMsgBizMapper;
import com.youyu.cotenant.service.cache.UserInfoCacheService;
import com.youyu.cotenant.utils.CurrentUserUtils;
import com.youyu.cotenant.utils.DateUtils;
import com.youyu.cotenant.utils.RedisUtils;
import com.youyu.cotenant.utils.SpringRedisUtils;
import com.youyu.cotenant.web.rest.vm.chat.*;
import com.youyu.cotenant.web.rest.vm.user.UserInfoOutVM;
import io.goeasy.GoEasy;
import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;
import com.youyu.cotenant.repository.biz.CotenantChatMsgBizMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ChatService {


    @Autowired
    private GoEasy goEasy;

    @Autowired
    private CotenantCommunicationMapper cotenantCommunicationMapper;

    @Autowired
    private CotenantChatMsgMapper cotenantChatMsgMapper;


    @Autowired
    private CotenantChatMsgBizMapper cotenantChatMsgBizMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private SpringRedisUtils mSpringRedisUtils;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CurrentUserUtils currentUserUtils;




    /**
     * 建立唯一通讯
     *
     * @param communicationInVM
     */
    public void buildCommunication(CommunicationInVM communicationInVM) {
        //如通道
        //Long userId = currentUserUtils.getCurrUserId();
        String channel = generateChannel(communicationInVM.getSendUserId(), communicationInVM.getReceiveUserId());
        //判断是否存在唯一通信id，如存在不插入，直接返回。如存在插入数据库
        CotenantCommunicationExample example = new CotenantCommunicationExample();
        example.createCriteria().andChannelEqualTo(channel);
        long l = cotenantCommunicationMapper.countByExample(example);
        if (l>0) {
            return;
        }
        CotenantCommunication cotenantCommunication = new CotenantCommunication();
        cotenantCommunication.setChannel(channel);
        cotenantCommunication.setId(GeneratorID.getId());
        cotenantCommunication.setSendUserId(communicationInVM.getSendUserId());
        cotenantCommunication.setReceiveUserId(communicationInVM.getReceiveUserId());
        cotenantCommunicationMapper.insert(cotenantCommunication);
    }

    /**
     * 获取聊天记录
     * @param  currUserId 发送人id
     * @param receiveUserId 接收人id
     */
    public List<ChatMessageOutVM> getMessage(Long currUserId,Long receiveUserId) {

        String channel = generateChannel(currUserId, receiveUserId);
        List<ChatMessageOutVM> result = (List<ChatMessageOutVM>)(Object)mSpringRedisUtils.lGetObjectAll(CotenantConstants.CHAT_RECEIVE_KEY + channel);
        if (result==null||result.size()==0) {
            result= cotenantChatMsgBizMapper.selectCotenantChatMsgList(channel);
            mSpringRedisUtils.lRightPushAllObject(CotenantConstants.CHAT_RECEIVE_KEY, (List<Object>)(Object)result);
        }
        return result;
    }

    /**
     * 获取聊天列表
     */
    public CommunicationOutVM list(Long userId) {
        CommunicationOutVM outVM = new CommunicationOutVM();
        //UserInfoOutVM userInfoOutVM = userService.detail();
        List<ChatMessageListOutVM> chatMessageListOutVMS = cotenantChatMsgBizMapper.selectCommunicationListByUserId(userId);
        outVM.setChatMessageListOutVMS(chatMessageListOutVMS);
       // outVM.setNick(userInfoOutVM.getNickName());
        return outVM;
    }

    /**
     * 发送消息存储持久层，redis中
     * @param chatMessageInVM 发送消息bena
     */
    @Async("asyncChatExecutor")
    public void send(ChatMessageInVM chatMessageInVM){
        String channel = generateChannel(chatMessageInVM.getSendUserId(), chatMessageInVM.getReceiveUserId());
        CotenantChatMsg cotenantChatMsg = new CotenantChatMsg();
        cotenantChatMsg.setReceiveUserId(chatMessageInVM.getReceiveUserId());
        cotenantChatMsg.setSendUserId(chatMessageInVM.getSendUserId());
        cotenantChatMsg.setId(GeneratorID.getId());
        cotenantChatMsg.setContent(chatMessageInVM.getContent());
        cotenantChatMsg.setSendTime(LocalDateTime.now());
        cotenantChatMsg.setChannel(generateChannel(chatMessageInVM.getSendUserId(),chatMessageInVM.getReceiveUserId()));
        cotenantChatMsgMapper.insert(cotenantChatMsg);
        //已接受的存放一个cache：chat_receive_key
        //缓存名称+channel  存放用户聊天信息，每次插入最后
        ChatMessageOutVM chatMessageOutVM = new ChatMessageOutVM();
        chatMessageOutVM.setContent(chatMessageInVM.getContent());
        chatMessageOutVM.setReceiveUserId(chatMessageInVM.getReceiveUserId());
        //chatMessageOutVM.setReceiveUserName();
        chatMessageOutVM.setSendTime(cotenantChatMsg.getSendTime());
        chatMessageOutVM.setSendUserId(chatMessageInVM.getSendUserId());
        //chatMessageOutVM.setSendUserName(chatMessageInVM.);
        mSpringRedisUtils.lRightPush(CotenantConstants.CHAT_RECEIVE_KEY+channel,chatMessageOutVM);
        sendSocket(chatMessageInVM.getReceiveUserId()+"",chatMessageInVM.getContent());
    }

    /**
     * 向指定channel推送消息
     *
     * @param receiveUserId 接受方的userid
     * @param content
     */
    public void sendSocket(String receiveUserId, String content) {
        goEasy.publish(receiveUserId, content, new PublishListener() {

            @Override
            public void onSuccess() {
                //消息发送成功处理
                log.info("send success!");
            }

            @Override
            public void onFailed(GoEasyError error) {
                //消息发送异常处理
                log.warn("send message failed,code:{},content:{}, channel:{}", error.getCode(), error.getContent(), receiveUserId);
            }

        });
    }

    /**
     * 通讯唯一通道生成策略(A-B，B-A唯一)
     *
     * @param sendUserId
     * @param receiveUserId
     */
    public String generateChannel(Long sendUserId, Long receiveUserId) {
        Long total = sendUserId + receiveUserId;
        return DigestUtils.md5Hex(String.valueOf(total));
    }

}
