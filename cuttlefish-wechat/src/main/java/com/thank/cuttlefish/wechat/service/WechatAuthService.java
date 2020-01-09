package com.thank.cuttlefish.wechat.service;

import com.thank.cuttlefish.dto.UserDto;
import com.thank.cuttlefish.dto.WechatLoginParam;
import me.chanjar.weixin.common.error.WxErrorException;

public interface WechatAuthService {
    /**
     * 登陆接口
     *
     * @param loginAuthParamVO
     * @return
     */
    UserDto login(WechatLoginParam loginAuthParamVO) throws WxErrorException;
}
