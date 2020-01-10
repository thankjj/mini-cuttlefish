package com.thank.cuttlefish.wechat.service.impl;

import com.thank.cuttlefish.dto.UserDto;
import com.thank.cuttlefish.dto.WechatLoginParam;
import com.thank.cuttlefish.user.api.UserApi;
import com.thank.cuttlefish.wechat.service.WechatAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatAuthServiceImpl implements WechatAuthService {

    @Autowired
    private UserApi userApi;

    @Override
    public UserDto login(WechatLoginParam loginAuthParamVO) {
        return userApi.login(loginAuthParamVO).getData();
    }
}
