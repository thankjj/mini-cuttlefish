package com.thank.cuttlefish.content.fallback;

import com.thank.cuttlefish.base.api.fallback.ApiFallback;
import com.thank.cuttlefish.common.utils.Result;
import com.thank.cuttlefish.content.api.ContentApi;
import com.thank.cuttlefish.pojo.Content;
import com.thank.cuttlefish.dto.ContentDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContentApiFallback extends ApiFallback<Content> implements ContentApi {

    @Override
    public Result<List<ContentDto>> queryListByRand(ContentDto contentDto) {
        System.out.println("ERROR........................");
        return Result.failure("500", "出错了......");
    }

    @Override
    public Result<Integer> addOrUpdateViewRecord(ContentDto contentDto) {
        return null;
    }

    @Override
    public Result<Integer> addOrUpdateThumbUp(ContentDto contentDto) {
        return null;
    }


}
