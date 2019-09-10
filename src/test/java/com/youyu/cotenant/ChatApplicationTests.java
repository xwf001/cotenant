package com.youyu.cotenant;

import com.youyu.cotenant.common.CotenantConstants;
import com.youyu.cotenant.service.ChatService;
import com.youyu.cotenant.utils.SpringRedisUtils;
import com.youyu.cotenant.web.rest.vm.chat.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatApplicationTests {

    @Autowired
    private ChatService chatService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Test
    public void remove(){

        stringRedisTemplate.delete(CotenantConstants.CHAT_RECEIVE_KEY+"8fef6dcd897195a0f356995f7b0135fd");

    }
    @Test
    public void buildCommunication() {
        //建立通道
        CommunicationInVM communicationInVM = new CommunicationInVM();
        communicationInVM.setReceiveUserId(598189994836963522l);
        communicationInVM.setSendUserId(598189994836963328l);
        chatService.buildCommunication(communicationInVM);
    }

    /**
     * 发送消息
     */
    @Test
    public void send() {
        ChatMessageInVM chatMessageInVM = new ChatMessageInVM();
        chatMessageInVM.setSendUserId(598189994836963522l);
        chatMessageInVM.setReceiveUserId(598189994836963328l);
        chatMessageInVM.setContent("你好3");
        chatService.send(chatMessageInVM);
        while (true){}

    }

    /**
     * 获取聊天记录
     */
    @Test
    public void getMessage() {
        List<ChatMessageOutVM> message = chatService.getMessage(598189994836963522l,598189994836963328l);
        for (ChatMessageOutVM chatMessageOutVM : message) {
            System.out.println("======================================");
            System.out.println("发送时间：" + chatMessageOutVM.getSendTime());
            System.out.println("发送人：" + chatMessageOutVM.getSendUserId());
            System.out.println("接受人：" + chatMessageOutVM.getReceiveUserId());
            System.out.println("内容：" + chatMessageOutVM.getContent());
        }
    }

    /**
     * 获取聊天列表
     */
    @Test
    public void list(){
        CommunicationOutVM communicationOutVM = chatService.list(598189994836963522l);
        System.out.println("登录人：" + communicationOutVM.getNick());
        for (ChatMessageListOutVM chatMessageListOutVM : communicationOutVM.getChatMessageListOutVMS()) {
            System.out.println("接受人："+chatMessageListOutVM.getReceiveUserName());
            System.out.println("最后消息："+chatMessageListOutVM.getLastContent());
            System.out.println("最后时间："+chatMessageListOutVM.getLastTime());
        }
    }


}
