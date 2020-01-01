package com.thank.cuttlefish.wechat.service;

import com.thank.cuttlefish.pojo.vo.LoginAuthParamVO;
import com.thank.cuttlefish.pojo.vo.LoginAuthResultVO;
import me.chanjar.weixin.common.error.WxErrorException;

public interface WechatAuthService {
    /**
     * 登陆接口
     *
     * @param loginAuthParamVO
     * @return
     */
    LoginAuthResultVO login(LoginAuthParamVO loginAuthParamVO) throws WxErrorException;
}
