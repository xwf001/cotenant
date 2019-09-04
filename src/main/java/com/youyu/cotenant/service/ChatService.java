package com.youyu.cotenant.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youyu.cotenant.repository.CotenantCommunicationMapper;
import com.youyu.cotenant.repository.CotenantUserInfoMapper;
import com.youyu.cotenant.utils.SpringRedisUtils;
import com.youyu.cotenant.web.rest.vm.chat.ChatMessageOutVM;
import com.youyu.cotenant.web.rest.vm.chat.CommunicationInVM;
import io.goeasy.GoEasy;
import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class ChatService {


    @Autowired
    private GoEasy goEasy;

    @Autowired
    private CotenantCommunicationMapper cotenantCommunicationMapper;

    @Autowired
    private CotenantUserInfoMapper cotenantUserInfoMapper;

    @Autowired
    private SpringRedisUtils mSpringRedisUtils;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 建立唯一通讯,并返回聊天记录
     *
     * @param communicationInVM
     */
    public ChatMessageOutVM buildCommunication(CommunicationInVM communicationInVM) throws IOException {
        ChatMessageOutVM chatMessageOutVM = null;
        return chatMessageOutVM;
    }

    /**
     * 获取聊天记录
     *
     * @param channel
     */
    public List<ChatMessageOutVM> getMessage(String channel) {
        List<ChatMessageOutVM> chatMessageOutVMList = null;

        return chatMessageOutVMList;
    }

    /**
     * 获取聊天列表
     */
    public void list() {

    }

    /**
     * 发送消息
     */
    public void send(){

    }

    /**
     * 向指定channel推送消息
     *
     * @param channel
     * @param content
     */
    public void send(String channel, String content) {
        goEasy.publish(channel, content, new PublishListener() {

            @Override
            public void onSuccess() {
                //消息发送成功处理
                log.info("send success!");
            }

            @Override
            public void onFailed(GoEasyError error) {
                //消息发送异常处理
                log.warn("send message failed,code:{},content:{}, channel:{}", error.getCode(), error.getContent(), channel);
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
