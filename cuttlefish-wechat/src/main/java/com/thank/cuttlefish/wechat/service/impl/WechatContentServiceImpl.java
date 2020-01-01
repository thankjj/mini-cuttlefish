package com.thank.cuttlefish.wechat.service.impl;

import com.thank.cuttlefish.content.api.ContentApi;
import com.thank.cuttlefish.pojo.Content;
import com.thank.cuttlefish.pojo.dto.ContentDto;
import com.thank.cuttlefish.wechat.service.WechatContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WechatContentServiceImpl implements WechatContentService {
    @Autowired
    private ContentApi contentApi;


    @Override
    public List<ContentDto> queryListByRand(ContentDto contentDto) {
        List<ContentDto> contents =  contentApi.queryListByRand(contentDto).getData();
        return contents;
    }

    @Override
    public Integer addOrUpdateViewRecord(ContentDto contentDto) {
        return contentApi.addOrUpdateViewRecord(contentDto).getData();
    }

    @Override
    public Integer addOrUpdateThumbUp(ContentDto contentDto) {
        return contentApi.addOrUpdateThumbUp(contentDto).getData();
    }
}
