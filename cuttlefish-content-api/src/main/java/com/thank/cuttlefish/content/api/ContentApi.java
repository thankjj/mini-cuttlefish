package com.thank.cuttlefish.content.api;

import com.thank.cuttlefish.common.api.Api;
import com.thank.cuttlefish.content.dto.ContentDto;
import com.thank.cuttlefish.content.fallback.ContentApiFallback;
import com.thank.cuttlefish.content.pojo.Content;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "cuttlefish-content", path = "content", fallback = ContentApiFallback.class)
public interface ContentApi extends Api<Content> {
    @PostMapping(value = "/queryListByRand")
    List<ContentDto> queryListByRand(@RequestBody ContentDto contentDto);

    // 新增/更改浏览记录
    @PostMapping(value = "/addOrUpdateViewRecord")
    Integer addOrUpdateViewRecord(@RequestBody ContentDto contentDto);

    // 新增/更改点赞记录
    @PostMapping(value = "/addOrUpdateThumbUp")
    Integer addOrUpdateThumbUp(@RequestBody ContentDto contentDto);

}
