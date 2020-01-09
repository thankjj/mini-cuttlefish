package com.thank.cuttlefish.wechat.service;

import com.thank.cuttlefish.dto.ContentDto;

import java.util.List;

public interface WechatContentService {
    List<ContentDto> queryListByRand(ContentDto contentDto);

    Integer addOrUpdateViewRecord(ContentDto contentDto);

    Integer addOrUpdateThumbUp(ContentDto contentDto);
}
