package com.thank.cuttlefish.content.api;

import com.thank.cuttlefish.base.api.Api;
import com.thank.cuttlefish.common.utils.Result;
import com.thank.cuttlefish.content.fallback.ContentApiFallback;
import com.thank.cuttlefish.pojo.Content;
import com.thank.cuttlefish.dto.ContentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "cuttlefish-content", path = "content", fallback = ContentApiFallback.class)
public interface ContentApi extends Api<Content> {
    @RequestMapping(value = "/queryListByRand")
    Result<List<ContentDto>> queryListByRand(@RequestBody ContentDto contentDto);

    // 新增/更改浏览记录
    @RequestMapping(value = "/addOrUpdateViewRecord")
    Result<Integer> addOrUpdateViewRecord(@RequestBody ContentDto contentDto);

    // 新增/更改点赞记录
    @RequestMapping(value = "/addOrUpdateThumbUp")
    Result<Integer> addOrUpdateThumbUp(@RequestBody ContentDto contentDto);

}
