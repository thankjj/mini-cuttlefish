package com.thank.cuttlefish.content.controller;

import com.thank.cuttlefish.base.api.BaseApi;
import com.thank.cuttlefish.content.api.ContentCategoryApi;
import com.thank.cuttlefish.content.service.ContentService;
import com.thank.cuttlefish.pojo.ContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/category")
public class ContentCategoryController extends BaseApi<ContentCategory> implements ContentCategoryApi {
    @Autowired
    private ContentService contentService;


}
