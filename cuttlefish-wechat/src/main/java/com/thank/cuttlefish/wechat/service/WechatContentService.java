package com.thank.cuttlefish.wechat.service;

import com.thank.cuttlefish.content.dto.ContentDto;
import com.thank.cuttlefish.content.pojo.Content;
import com.thank.cuttlefish.wechat.vo.ContentParamVO;

import java.util.List;

public interface WechatContentService {
    List<ContentDto> queryListByRand(ContentDto contentDto);

    Integer addOrUpdateViewRecord(ContentDto contentDto);

    Integer addOrUpdateThumbUp(ContentDto contentDto);
}
