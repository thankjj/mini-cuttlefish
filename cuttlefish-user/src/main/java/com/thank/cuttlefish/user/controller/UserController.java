package com.thank.cuttlefish.user.controller;

import com.thank.cuttlefish.common.api.BaseApi;
import com.thank.cuttlefish.user.service.UserService;
import com.thank.cuttlefish.user.api.UserApi;
import com.thank.cuttlefish.user.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
public class UserController extends BaseApi<User> implements UserApi {

//    @Autowired
//    private UserService userService;

//    @Override
//    public Result<String> userLogin(User user) {
//        // 判断是不是首次登录
//        userService.userLogin(user);
//
//        return null;
//    }


}