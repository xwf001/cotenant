package com.youyu.cotenant.web.rest.vm.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserOutVM {

    private String id;

    private String mobile;

    private Integer status;

    @JsonProperty("unread_count")
    private int unreadCount; //未读消息数量
}
