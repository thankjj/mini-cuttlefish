package com.thank.cuttlefish.wechat.controller;

import com.thank.cuttlefish.common.utils.Result;
import com.thank.cuttlefish.dto.UserDto;
import com.thank.cuttlefish.dto.WechatLoginParam;
import com.thank.cuttlefish.wechat.service.WechatAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/wechat/auth")
public class WechatAuthController {

    @Autowired
    private WechatAuthService wechatAuthService;

    @PostMapping("/login")
    public Result<UserDto> loginByWeixin(@RequestBody WechatLoginParam loginAuthParamVO) {
        return Result.success(wechatAuthService.login(loginAuthParamVO));
    }

}
