package com.thank.cuttlefish.content.service.impl;

import com.thank.cuttlefish.common.service.BaseService;
import com.thank.cuttlefish.content.dto.ContentDto;
import com.thank.cuttlefish.content.mapper.ContentMapper;
import com.thank.cuttlefish.content.pojo.Content;
import com.thank.cuttlefish.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class ContentServiceImpl extends BaseService<Content> implements ContentService {
    @Autowired
    private ContentMapper contentMapper;

    @Override
    public List<ContentDto> queryListByRand(ContentDto contentDto) {
        return contentMapper.queryListByRand(contentDto);
    }
}
