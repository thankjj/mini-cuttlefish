package com.thank.cuttlefish.content.service;


import com.thank.cuttlefish.base.service.MyService;
import com.thank.cuttlefish.pojo.ContentCategory;

import java.util.List;
import java.util.Map;

public interface ContentCategoryService extends MyService<ContentCategory> {

    List<ContentCategory> queryContentCategoryValidList();

}
