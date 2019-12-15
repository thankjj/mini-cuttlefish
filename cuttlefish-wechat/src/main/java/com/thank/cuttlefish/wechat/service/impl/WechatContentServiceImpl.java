package com.thank.cuttlefish.wechat.service.impl;

import com.thank.cuttlefish.common.utils.Criteria;
import com.thank.cuttlefish.content.api.ContentApi;
import com.thank.cuttlefish.content.dto.ContentDto;
import com.thank.cuttlefish.content.pojo.Content;
import com.thank.cuttlefish.wechat.service.WechatContentService;
import com.thank.cuttlefish.wechat.vo.ContentParamVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WechatContentServiceImpl implements WechatContentService {
    @Autowired
    private ContentApi contentApi;


    @Override
    public List<ContentDto> queryListByRand(ContentDto contentDto) {
        List<ContentDto> contents =  contentApi.queryListByRand(contentDto);
        return contents;
    }
}
