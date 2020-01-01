package com.thank.cuttlefish.content.controller;

import com.thank.cuttlefish.base.BaseApi;
import com.thank.cuttlefish.common.utils.Result;
import com.thank.cuttlefish.content.api.ContentApi;
import com.thank.cuttlefish.content.service.ContentService;
import com.thank.cuttlefish.pojo.Content;
import com.thank.cuttlefish.pojo.dto.ContentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/content")
public class ContentController extends BaseApi<Content> implements ContentApi {
    @Autowired
    private ContentService contentService;

    @Override
//    @RequestMapping("/queryListByRand")
    public Result<List<ContentDto>> queryListByRand(ContentDto contentDto) {
        return Result.success(contentService.queryListByRand(contentDto));
    }

    @Override
//    @RequestMapping("/addOrUpdateViewRecord")
    public Result<Integer> addOrUpdateViewRecord(ContentDto contentDto) {
        return Result.success(contentService.addOrUpdateViewRecord(contentDto));
    }

    @Override
//    @RequestMapping("/addOrUpdateThumbUp")
    public Result<Integer> addOrUpdateThumbUp(ContentDto contentDto) {
        return Result.success(contentService.addOrUpdateThumbUp(contentDto));
    }



}
