package com.thank.cuttlefish.user.api;

import com.thank.cuttlefish.common.api.Api;
import com.thank.cuttlefish.user.fallback.UserApiFallback;
import com.thank.cuttlefish.user.po.User;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "cuttlefish-user", path = "user", fallback = UserApiFallback.class)
public interface UserApi  extends Api<User> {


}
