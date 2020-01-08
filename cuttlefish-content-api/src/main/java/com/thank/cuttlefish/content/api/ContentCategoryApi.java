package com.thank.cuttlefish.content.api;

import com.thank.cuttlefish.base.api.Api;
import com.thank.cuttlefish.common.utils.Result;
import com.thank.cuttlefish.content.fallback.ContentCategoryApiFallback;
import com.thank.cuttlefish.pojo.ContentCategory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "cuttlefish-content", path = "category/", fallback = ContentCategoryApiFallback.class)
public interface ContentCategoryApi extends Api<ContentCategory> {
    @RequestMapping(value = "/queryContentCategoryValidList")
    Result<List<ContentCategory>> queryContentCategoryValidList();

}
