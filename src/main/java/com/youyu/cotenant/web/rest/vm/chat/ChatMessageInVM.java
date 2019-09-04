package com.youyu.cotenant.web.rest.vm.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChatMessageInVM {


    @JsonProperty("send_user_id")
    private Long sendUserId;

    @JsonProperty("receive_user_id")
    private Long receiveUserId;

    private String content;

}
