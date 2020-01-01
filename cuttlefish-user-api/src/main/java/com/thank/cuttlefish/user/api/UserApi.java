package com.thank.cuttlefish.user.api;

import com.thank.cuttlefish.base.api.Api;
import com.thank.cuttlefish.pojo.User;
import com.thank.cuttlefish.user.fallback.UserApiFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "cuttlefish-user", path = "user", fallback = UserApiFallback.class)
public interface UserApi  extends Api<User> {


}
