package com.thank.cuttlefish.user.controller;

import com.thank.cuttlefish.base.api.BaseApi;
import com.thank.cuttlefish.common.utils.Result;
import com.thank.cuttlefish.pojo.User;
import com.thank.cuttlefish.dto.UserDto;
import com.thank.cuttlefish.dto.WechatLoginParam;
import com.thank.cuttlefish.user.api.UserApi;
import com.thank.cuttlefish.user.service.UserService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController extends BaseApi<User> implements UserApi {

    @Autowired
    private UserService userService;

    @Override
    public Result<UserDto> login(WechatLoginParam loginAuthParamVO) {
        try {
            return Result.success(userService.login(loginAuthParamVO));
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return null;
    }


}
