package com.youyu.cotenant.web.rest.vm.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageOutVM {

    @JsonProperty("send_user_id")
    private Long sendUserId;

    @JsonProperty("receive_user_id")
    private Long receiveUserId;

    @JsonProperty("send_user_name")
    private String sendUserName;

    @JsonProperty("receive_user_name")
    private String receiveUserName;

    @JsonProperty("send_user_head")
    private String sendUserHead;

    @JsonProperty("receive_user_head")
    private String receiveUserHead;

    private String content;

    @JsonProperty("send_time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime sendTime;

}
