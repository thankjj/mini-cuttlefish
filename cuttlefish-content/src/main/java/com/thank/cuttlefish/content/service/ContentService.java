package com.thank.cuttlefish.content.service;

import com.thank.cuttlefish.base.service.MyService;
import com.thank.cuttlefish.pojo.Content;
import com.thank.cuttlefish.dto.ContentDto;

import java.util.List;

public interface ContentService extends MyService<Content> {
    List<ContentDto> queryListByRand(ContentDto contentDto);

    // 新增/更改浏览记录
    Integer addOrUpdateViewRecord(ContentDto contentDto);

    // 新增/更改点赞记录
    Integer addOrUpdateThumbUp(ContentDto contentDto);

}
