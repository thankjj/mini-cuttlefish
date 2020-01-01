package com.thank.cuttlefish.content.service.impl;

import com.thank.cuttlefish.base.service.impl.MyServiceImpl;
import com.thank.cuttlefish.content.service.ContentCategoryService;
import com.thank.cuttlefish.pojo.ContentCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ContentCategoryServiceImpl extends MyServiceImpl<ContentCategory> implements ContentCategoryService {



}
