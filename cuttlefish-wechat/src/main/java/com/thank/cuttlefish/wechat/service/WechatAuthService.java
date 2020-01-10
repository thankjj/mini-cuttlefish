package com.thank.cuttlefish.wechat.service;

import com.thank.cuttlefish.dto.UserDto;
import com.thank.cuttlefish.dto.WechatLoginParam;

public interface WechatAuthService {
    /**
     * 登陆接口
     *
     * @param loginAuthParamVO
     * @return
     */
    UserDto login(WechatLoginParam loginAuthParamVO) ;
}
