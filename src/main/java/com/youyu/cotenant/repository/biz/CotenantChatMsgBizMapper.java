package com.youyu.cotenant.repository.biz;

import com.youyu.cotenant.web.rest.vm.chat.ChatMessageListOutVM;
import com.youyu.cotenant.web.rest.vm.chat.ChatMessageOutVM;
import com.youyu.cotenant.web.rest.vm.chat.CommunicationOutVM;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotenantChatMsgBizMapper {

    List<ChatMessageOutVM> selectCotenantChatMsgList(@Param("channel")String channel);


    List<ChatMessageListOutVM> selectCommunicationListByUserId(@Param("userId") Long userId);

}