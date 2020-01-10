package com.thank.cuttlefish.wechat.controller;

import com.thank.cuttlefish.common.utils.Result;
import com.thank.cuttlefish.pojo.ContentCategory;
import com.thank.cuttlefish.wechat.service.WechatContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/wechat/content/category")
public class WechatContentCategoryController {

    @Autowired
    private WechatContentCategoryService wechatContentCategoryService;

    @GetMapping("/queryContentCategoryValidList")
    public Result<List<ContentCategory>> queryList() {
        return Result.success(wechatContentCategoryService.queryList());
    }

}
