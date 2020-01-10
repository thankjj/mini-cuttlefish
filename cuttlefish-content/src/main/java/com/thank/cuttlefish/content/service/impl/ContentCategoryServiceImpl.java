package com.thank.cuttlefish.content.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.thank.cuttlefish.base.service.impl.MyServiceImpl;
import com.thank.cuttlefish.common.constant.CuttlefishRedisConstant;
import com.thank.cuttlefish.content.mapper.ContentCategoryMapper;
import com.thank.cuttlefish.content.service.ContentCategoryService;
import com.thank.cuttlefish.pojo.ContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ContentCategoryServiceImpl extends MyServiceImpl<ContentCategory> implements ContentCategoryService {

    @Autowired
    private ContentCategoryMapper contentCategoryMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<ContentCategory> queryContentCategoryValidList() {
        List<ContentCategory> contentCategoryList = redisTemplate.opsForList().range(CuttlefishRedisConstant.REDIS_KEY_CONTENT_CATEGORY_LIST, 0, -1);
        if (contentCategoryList.size() == 0){
            EntityWrapper<ContentCategory> queryWrapper = new EntityWrapper<>();
            queryWrapper.eq("category_status", 1);
            queryWrapper.orderBy("priority", false);
            contentCategoryList = contentCategoryMapper.selectList(queryWrapper);
            redisTemplate.opsForList().leftPushAll(CuttlefishRedisConstant.REDIS_KEY_CONTENT_CATEGORY_LIST, contentCategoryList);
        }
        return contentCategoryList;
    }
}
