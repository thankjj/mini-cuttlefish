package com.thank.cuttlefish.content.fallback;

import com.thank.cuttlefish.base.api.fallback.ApiFallback;
import com.thank.cuttlefish.common.utils.Result;
import com.thank.cuttlefish.content.api.ContentCategoryApi;
import com.thank.cuttlefish.pojo.ContentCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContentCategoryApiFallback extends ApiFallback<ContentCategory> implements ContentCategoryApi {

    @Override
    public Result<List<ContentCategory>> queryContentCategoryValidList() {
        return null;
    }


}
