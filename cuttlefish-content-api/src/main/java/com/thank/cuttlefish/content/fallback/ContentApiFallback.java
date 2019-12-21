package com.thank.cuttlefish.content.fallback;

import com.thank.cuttlefish.common.fallback.ApiFallback;
import com.thank.cuttlefish.content.api.ContentApi;
import com.thank.cuttlefish.content.dto.ContentDto;
import com.thank.cuttlefish.content.pojo.Content;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContentApiFallback extends ApiFallback<Content> implements ContentApi {

    @Override
    public List<ContentDto> queryListByRand(ContentDto contentDto) {
        return null;
    }

    @Override
    public Integer addOrUpdateViewRecord(ContentDto contentDto) {
        return null;
    }

    @Override
    public Integer addOrUpdateThumbUp(ContentDto contentDto) {
        return null;
    }


}
