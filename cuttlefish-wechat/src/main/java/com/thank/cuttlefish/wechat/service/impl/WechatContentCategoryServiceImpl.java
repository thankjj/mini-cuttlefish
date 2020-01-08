package com.thank.cuttlefish.wechat.service.impl;

import com.thank.cuttlefish.content.api.ContentCategoryApi;
import com.thank.cuttlefish.pojo.ContentCategory;
import com.thank.cuttlefish.pojo.dto.WrapperDto;
import com.thank.cuttlefish.wechat.service.WechatContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WechatContentCategoryServiceImpl implements WechatContentCategoryService {
    @Autowired
    private ContentCategoryApi contentCategoryApi;


    @Override
    public List<ContentCategory> queryList() {
//        WrapperDto<ContentCategory> wrapperDto = new WrapperDto<>();
//        wrapperDto.addWhereCondition("category_status", 1);
//        wrapperDto.addOrderByCondition("priority", false);
//
//        List<ContentCategory> contentCategories = contentCategoryApi.selectList(wrapperDto).getData();
        List<ContentCategory> contentCategories = contentCategoryApi.queryContentCategoryValidList().getData();
        return contentCategories;
    }


}
