package com.thank.cuttlefish.content.service;

import com.thank.cuttlefish.common.service.IService;
import com.thank.cuttlefish.content.dto.ContentDto;
import com.thank.cuttlefish.content.pojo.Content;

import java.util.List;
import java.util.Map;

public interface ContentService extends IService<Content> {
    List<ContentDto> queryListByRand(ContentDto contentDto);

    // 新增/更改浏览记录
    Integer addOrUpdateViewRecord(ContentDto contentDto);

    // 新增/更改点赞记录
    Integer addOrUpdateThumbUp(ContentDto contentDto);


}
