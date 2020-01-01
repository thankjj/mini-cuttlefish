package com.thank.cuttlefish.user.fallback;

import com.thank.cuttlefish.base.api.fallback.ApiFallback;
import com.thank.cuttlefish.pojo.User;
import com.thank.cuttlefish.user.api.UserApi;
import org.springframework.stereotype.Component;

@Component
public class UserApiFallback extends ApiFallback<User> implements UserApi {
//    @Override
//    public Result<String> userLogin(String wechatId) {
//        return null;
//    }

}
