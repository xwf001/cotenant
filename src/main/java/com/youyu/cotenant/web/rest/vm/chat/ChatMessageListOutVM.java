package com.youyu.cotenant.web.rest.vm.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChatMessageListOutVM {

    @JsonProperty("receive_user_id")
    private String receiveUserId;

    @JsonProperty("receive_user_name")
    private String receiveUserName;

    @JsonProperty("receive_user_head")
    private String receiveUserHead;

    @JsonProperty("last_content")
    private String lastContent;

    @JsonProperty("last_time")
    private String lastTime;
}
