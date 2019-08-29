package com.youyu.cotenant.web.rest.vm.group;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GroupListOutVM {

    private String id;

    private String province;

    private String city;

    private String district;

    private String title;

    private String leader;

    private Integer cotenantCount;

    private Integer cotenantType;

    private Integer status;

    private String chamberImgUrl;

    private LocalDateTime createdTime;

}
