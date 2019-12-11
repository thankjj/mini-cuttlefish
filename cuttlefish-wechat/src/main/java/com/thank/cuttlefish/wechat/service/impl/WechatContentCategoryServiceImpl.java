package com.thank.cuttlefish.wechat.service.impl;

import com.thank.cuttlefish.common.enums.ValidStatus;
import com.thank.cuttlefish.common.utils.Criteria;
import com.thank.cuttlefish.content.api.ContentCategoryApi;
import com.thank.cuttlefish.content.pojo.ContentCategory;
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
        ContentCategory contentCategory = contentCategoryApi.queryOneByCriteria(Criteria.of(ContentCategory.class)).getData();
        List<ContentCategory> contentCategories =  contentCategoryApi.queryByCriteria(Criteria.of(ContentCategory.class)
//                .andEqualTo(ContentCategory::getCategoryStatus, ValidStatus.VALID.getValue())
//                .sort(ContentCategory::getPriority)
                ).getData();
        return contentCategories;
    }
}
