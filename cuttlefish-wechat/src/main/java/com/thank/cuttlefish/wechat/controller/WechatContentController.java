package com.thank.cuttlefish.wechat.controller;

import com.thank.cuttlefish.common.utils.Result;
import com.thank.cuttlefish.pojo.dto.ContentDto;
import com.thank.cuttlefish.wechat.service.WechatContentService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // 新增/更改浏览记录
    @GetMapping("/addOrUpdateViewRecord")
    public Result<Integer> addOrUpdateViewRecord(ContentDto contentDto){
        return Result.success(wechatContentService.addOrUpdateViewRecord(contentDto));
    }

    // 新增/更改点赞记录
    @GetMapping("/addOrUpdateThumbUp")
    public Result<Integer> addOrUpdateThumbUp(ContentDto contentDto){
        return Result.success(wechatContentService.addOrUpdateThumbUp(contentDto));
    }

}
