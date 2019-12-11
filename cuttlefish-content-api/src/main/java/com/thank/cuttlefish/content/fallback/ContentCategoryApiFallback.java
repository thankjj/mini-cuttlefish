package com.thank.cuttlefish.content.fallback;

import com.thank.cuttlefish.common.fallback.ApiFallback;
import com.thank.cuttlefish.content.api.ContentCategoryApi;
import com.thank.cuttlefish.content.pojo.ContentCategory;
import org.springframework.stereotype.Component;

@Component
public class ContentCategoryApiFallback extends ApiFallback<ContentCategory> implements ContentCategoryApi {


}
