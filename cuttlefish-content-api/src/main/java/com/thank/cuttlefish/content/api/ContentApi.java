package com.thank.cuttlefish.content.api;

import com.thank.cuttlefish.common.api.Api;
import com.thank.cuttlefish.content.dto.ContentDto;
import com.thank.cuttlefish.content.fallback.ContentApiFallback;
import com.thank.cuttlefish.content.pojo.Content;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "cuttlefish-content", path = "content", fallback = ContentApiFallback.class)
public interface ContentApi extends Api<Content> {
    @PostMapping(value = "/queryListByRand")
    List<ContentDto> queryListByRand(ContentDto contentDto);

}
