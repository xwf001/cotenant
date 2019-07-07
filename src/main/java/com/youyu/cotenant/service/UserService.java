package com.youyu.cotenant.service;

import com.youyu.cotenant.entity.CotenantUser;
import com.youyu.cotenant.entity.CotenantUserExample;
import com.youyu.cotenant.repository.CotenantUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    CotenantUserMapper cotenantUserMapper;

    public CotenantUser selectUserByUserName(String mobile) {
        CotenantUserExample cue = new CotenantUserExample();
        cue.createCriteria().andMobileEqualTo(mobile).andEnabledEqualTo(true);
        List<CotenantUser> userList = cotenantUserMapper.selectByExample(cue);
        return CollectionUtils.isEmpty(userList) ? null : userList.get(0);
    }


}
