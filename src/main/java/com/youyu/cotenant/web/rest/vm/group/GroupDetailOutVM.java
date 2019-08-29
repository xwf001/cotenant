package com.youyu.cotenant.web.rest.vm.group;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GroupDetailOutVM {

    private String id;

    private String province;

    private String city;

    private String district;

    private String title;

    private String leader;

    private Integer cotenantCount;

    private Integer cotenantType;

    private Integer status;

    private String cotenantDescription;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String chamberImgUrl;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String chamberDescription;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String chamberVideoUrl;

    private LocalDateTime createdTime;

    @JsonUnwrapped
    private List<CotenantListOutVM> cotenantList;

}
