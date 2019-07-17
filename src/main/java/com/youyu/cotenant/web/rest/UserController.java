package com.youyu.cotenant.web.rest;

import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.service.UserService;
import com.youyu.cotenant.web.rest.vm.user.UserRegisterInVM;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseResult register(@Valid @RequestBody UserRegisterInVM userRegisterInVM) {
        int result = userService.register(userRegisterInVM);
        return result == NumberUtils.INTEGER_ZERO ? ResponseResult.fail() : ResponseResult.success();
    }

}
