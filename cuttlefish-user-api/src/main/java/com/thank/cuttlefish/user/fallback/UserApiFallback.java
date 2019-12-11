package com.thank.cuttlefish.user.fallback;

import com.thank.cuttlefish.common.fallback.ApiFallback;
import com.thank.cuttlefish.user.api.UserApi;
import com.thank.cuttlefish.user.po.User;
import org.springframework.stereotype.Component;

@Component
public class UserApiFallback extends ApiFallback<User> implements UserApi {
//    @Override
//    public Result<String> userLogin(String wechatId) {
//        return null;
//    }

}
