package com.thank.cuttlefish.content.controller;

import com.thank.cuttlefish.common.api.BaseApi;
import com.thank.cuttlefish.content.api.ContentCategoryApi;
import com.thank.cuttlefish.content.pojo.ContentCategory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/content/category")
public class ContentCategoryController extends BaseApi<ContentCategory> implements ContentCategoryApi {

}
