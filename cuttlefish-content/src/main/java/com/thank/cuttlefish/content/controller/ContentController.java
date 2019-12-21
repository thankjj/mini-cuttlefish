package com.thank.cuttlefish.content.controller;

import com.thank.cuttlefish.common.api.BaseApi;
import com.thank.cuttlefish.content.api.ContentApi;
import com.thank.cuttlefish.content.dto.ContentDto;
import com.thank.cuttlefish.content.pojo.Content;
import com.thank.cuttlefish.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/content")
public class ContentController extends BaseApi<Content> implements ContentApi {

    @Autowired
    private ContentService contentService;

    @Override
    public List<ContentDto> queryListByRand(ContentDto contentDto) {
        return contentService.queryListByRand(contentDto);
    }

    // 新增/更改浏览记录
    @Override
    public Integer addOrUpdateViewRecord(ContentDto contentDto){
        return contentService.addOrUpdateViewRecord(contentDto);
    }

    // 新增/更改点赞记录
    @Override
    public Integer addOrUpdateThumbUp(ContentDto contentDto){
        return contentService.addOrUpdateThumbUp(contentDto);
    }

}