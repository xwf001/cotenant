package com.youyu.cotenant.web.rest;

import com.youyu.cotenant.common.ResponseResult;
import com.youyu.cotenant.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @GetMapping("/send")
    public ResponseResult send(@Valid String phone) {
        smsService.sendSms(phone);
        return ResponseResult.success();
    }
}
