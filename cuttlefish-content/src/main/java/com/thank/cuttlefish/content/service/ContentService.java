package com.thank.cuttlefish.content.service;

import com.thank.cuttlefish.common.service.IService;
import com.thank.cuttlefish.content.dto.ContentDto;
import com.thank.cuttlefish.content.pojo.Content;

import java.util.List;

public interface ContentService extends IService<Content> {
    List<ContentDto> queryListByRand(ContentDto contentDto);

}
