package com.thank.cuttlefish.user.service.impl;

import com.thank.cuttlefish.base.service.impl.MyServiceImpl;
import com.thank.cuttlefish.pojo.User;
import com.thank.cuttlefish.user.mapper.UserMapper;
import com.thank.cuttlefish.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends MyServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

}
