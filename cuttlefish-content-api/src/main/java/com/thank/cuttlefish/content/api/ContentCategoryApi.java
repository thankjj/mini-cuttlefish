package com.thank.cuttlefish.content.api;

import com.thank.cuttlefish.base.api.Api;
import com.thank.cuttlefish.content.fallback.ContentCategoryApiFallback;
import com.thank.cuttlefish.pojo.ContentCategory;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "cuttlefish-content", path = "category/", fallback = ContentCategoryApiFallback.class)
public interface ContentCategoryApi extends Api<ContentCategory> {

}
