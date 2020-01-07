package com.thank.cuttlefish.user.controller;

import com.thank.cuttlefish.base.api.BaseApi;
import com.thank.cuttlefish.pojo.User;
import com.thank.cuttlefish.user.api.UserApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController extends BaseApi<User> implements UserApi {


}
