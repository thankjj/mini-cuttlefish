package com.thank.cuttlefish.user.api;

import com.thank.cuttlefish.base.api.Api;
import com.thank.cuttlefish.common.utils.Result;
import com.thank.cuttlefish.pojo.User;
import com.thank.cuttlefish.dto.UserDto;
import com.thank.cuttlefish.dto.WechatLoginParam;
import com.thank.cuttlefish.user.fallback.UserApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "cuttlefish-user", path = "user", fallback = UserApiFallback.class)
public interface UserApi  extends Api<User> {
    // 微信用户登录
    @RequestMapping(value = "/login")
    Result<UserDto> login(@RequestBody WechatLoginParam loginAuthParamVO);

}
