package com.thank.cuttlefish.content.controller;

import com.thank.cuttlefish.common.api.BaseApi;
import com.thank.cuttlefish.content.api.ContentApi;
import com.thank.cuttlefish.content.pojo.Content;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/content")
public class ContentController extends BaseApi<Content> implements ContentApi {


}