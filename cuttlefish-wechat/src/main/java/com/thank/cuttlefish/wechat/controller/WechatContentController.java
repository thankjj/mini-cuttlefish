package com.thank.cuttlefish.wechat.controller;

import com.thank.cuttlefish.common.utils.Result;
import com.thank.cuttlefish.content.dto.ContentDto;
import com.thank.cuttlefish.content.pojo.Content;
import com.thank.cuttlefish.wechat.service.WechatContentService;
import com.thank.cuttlefish.wechat.vo.ContentParamVO;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/wechat/content/")
public class WechatContentController {

    @Autowired
    private WechatContentService wechatContentService;

    @GetMapping("/queryListByRand")
    public Result<List<ContentDto>> queryListByRand(ContentDto contentDto) throws WxErrorException {
        return Result.success(wechatContentService.queryListByRand(contentDto));
    }

}
