package com.youyu.cotenant.web.rest.vm.group;

import com.youyu.cotenant.entity.CotenantGroup;
import lombok.Data;

import static com.youyu.cotenant.common.CotenantConstants.GROUP_STATUS.DEFAULT_STATUS;

@Data
public class GroupInVM {

    private String province;

    private String city;

    private String district;

    private String title;

    private Integer cotenantCount;

    private Integer cotenantType;

    private Integer status;

    private String cotenantDescription;

    private String chamberImgUrl;

    private String chamberDescription;

    private String chamberVideoUrl;


    public CotenantGroup buildCotenantGroup() {
        CotenantGroup cotenantGroup = new CotenantGroup();
        cotenantGroup.setProvince(province);
        cotenantGroup.setCity(city);
        cotenantGroup.setDistrict(district);
        cotenantGroup.setTitle(title);
        cotenantGroup.setCotenantCount(cotenantCount);
        cotenantGroup.setCotenantType(cotenantType);
        cotenantGroup.setStatus(DEFAULT_STATUS);
        cotenantGroup.setCotenantDescription(cotenantDescription);
        cotenantGroup.setChamberImgUrl(chamberImgUrl);
        cotenantGroup.setChamberDescription(chamberDescription);
        cotenantGroup.setChamberVideoUrl(chamberVideoUrl);
        return cotenantGroup;
    }


}
