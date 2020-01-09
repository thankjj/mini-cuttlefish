package com.thank.cuttlefish.user.service;


import com.thank.cuttlefish.base.service.MyService;
import com.thank.cuttlefish.pojo.User;
import com.thank.cuttlefish.dto.UserDto;
import com.thank.cuttlefish.dto.WechatLoginParam;
import me.chanjar.weixin.common.error.WxErrorException;

public interface UserService extends MyService<User> {
    UserDto login(WechatLoginParam loginAuthParamVO) throws WxErrorException;
}
