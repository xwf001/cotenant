package com.youyu.cotenant.service;

import com.youyu.cotenant.common.GeneratorID;
import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.common.ResultCode;
import com.youyu.cotenant.entity.CotenantUser;
import com.youyu.cotenant.entity.CotenantUserExample;
import com.youyu.cotenant.exception.BizException;
import com.youyu.cotenant.repository.CotenantUserMapper;
import com.youyu.cotenant.utils.RedisUtils;
import com.youyu.cotenant.web.rest.vm.user.UserRegisterInVM;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.youyu.cotenant.common.CotenantConstants.CODE_CACHE;

@Service
@Slf4j
public class UserService {

    @Autowired
    CotenantUserMapper cotenantUserMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CotenantUser selectUserByUserName(String mobile) {
        CotenantUserExample cue = new CotenantUserExample();
        cue.createCriteria().andMobileEqualTo(mobile).andEnabledEqualTo(true);
        List<CotenantUser> userList = cotenantUserMapper.selectByExample(cue);
        return CollectionUtils.isEmpty(userList) ? null : userList.get(0);
    }

    /**
     * 用户注册
     *
     * @param userRegisterInVM
     * @return
     */
    public int register(UserRegisterInVM userRegisterInVM) {
        String mobile = userRegisterInVM.getMobile();
        String code = userRegisterInVM.getCode();
        String password = userRegisterInVM.getPassword();
        String verificationCode = redisUtils.getCache(CODE_CACHE + mobile);
        if (selectUserByUserName(mobile) != null) {
            throw new BizException(ResponseResult.fail(ResultCode.USER_EXISZTS));
        }
        if (!StringUtils.equalsIgnoreCase(verificationCode, code)) {
            throw new BizException(ResponseResult.fail(ResultCode.SMS_CODE_ERROR));
        }
        CotenantUser cotenantUser = userRegisterInVM.buildCotenantUser();
        cotenantUser.setId(GeneratorID.getId());
        cotenantUser.setPassword(passwordEncoder.encode(password));
        return cotenantUserMapper.insertSelective(cotenantUser);
    }


}
