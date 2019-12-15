package com.thank.cuttlefish.user.service.impl;

import com.thank.cuttlefish.common.service.BaseService;
import com.thank.cuttlefish.user.service.UserService;
import com.thank.cuttlefish.user.po.User;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserServiceImpl extends BaseService<User> implements UserService {

//    @Autowired
//    private UserMapper userMapper;
//
//    @Override
//    public User userLogin(User user) {
//        // 初次登录
//        if (isFirstLogin(user)){
//
//        }else{
//
//        }
//
//        return user;
//    }
//
//    public boolean isFirstLogin(User user){
//        return userMapper.selectOne(user) == null;
//    }


}
