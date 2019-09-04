package com.youyu.cotenant.web.rest.vm.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommunicationInVM {

    @JsonProperty("send_user_id")
    private Long sendUserId;

    @JsonProperty("receive_user_id")
    private Long receiveUserId;
}
