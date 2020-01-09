package com.thank.cuttlefish.content.service.impl;

import com.thank.cuttlefish.base.service.impl.MyServiceImpl;
import com.thank.cuttlefish.common.constant.CuttlefishRedisKeyConstant;
import com.thank.cuttlefish.content.mapper.ContentMapper;
import com.thank.cuttlefish.content.service.ContentService;
import com.thank.cuttlefish.pojo.Content;
import com.thank.cuttlefish.dto.ContentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.thank.cuttlefish.base.utils.WebUtil;


@Service
@Transactional
public class ContentServiceImpl extends MyServiceImpl<Content> implements ContentService {
    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<ContentDto> queryListByRand(ContentDto contentDto) {
        return contentMapper.queryListByRand(contentDto);
    }

    @Override
    public Integer addOrUpdateViewRecord(ContentDto contentDto) {
        Date now = new Date();
        Map<String, Object> map = new HashMap<String, Object>();
//        if ("".equals(contentDto.getAuthorId())){
//            contentDto.setAuthorId(WebUtil.getInstance().getIpAddress());
//        }
        map.put("userId", contentDto.getAuthorId());
        map.put("contentId", contentDto.getId());

        // 用户浏览记录总数
        Integer count = contentMapper.isViewRecordExist(map);
        if (count == 0){
            // 新增
            map.put("createTime", now);
            contentMapper.addViewRecord(map);
        }else {
            // 修改
            map.put("updateTime", now);
            contentMapper.updateViewRecord(map);
        }
        return contentMapper.getContentViewCount(contentDto.getId());

    }

    @Override
    public Integer addOrUpdateThumbUp(ContentDto contentDto) {
        Integer thumbStatus = contentDto.getThumbUpStatus();
        String cacheKeyPrefix = thumbStatus == 0 ? CuttlefishRedisKeyConstant.REDIS_KEY_USER_THUMBUP_CANCEL_PREFIX : CuttlefishRedisKeyConstant.REDIS_KEY_USER_THUMBUP_PREFIX;
        String cacheCurrentKey = cacheKeyPrefix + contentDto.getId();
        String cacheOppositeKey = (thumbStatus == 1 ? CuttlefishRedisKeyConstant.REDIS_KEY_USER_THUMBUP_CANCEL_PREFIX : CuttlefishRedisKeyConstant.REDIS_KEY_USER_THUMBUP_PREFIX) + contentDto.getId();
        String cacheValue = String.valueOf(contentDto.getAuthorId());
        // 判断当前缓存中是否有此记录
        if (!redisTemplate.opsForSet().isMember(cacheCurrentKey, cacheValue)){
            redisTemplate.opsForSet().add(cacheCurrentKey, cacheValue);
        }
        // 判断对立缓存中是否有此记录
        if (redisTemplate.opsForSet().isMember(cacheOppositeKey, cacheValue)){
            redisTemplate.delete(cacheOppositeKey);
        }
        return thumbStatus;

//        Date now = new Date();
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("userId", contentDto.getAuthorId());
//        map.put("contentId", contentDto.getId());

//        // 是否存在用户点赞记录
//        Integer count = contentMapper.isThumbUpExist(map);
//        map.put("thumbUpStatus", contentDto.getThumbUpStatus());
//        if (count == 0){
//            // 新增
//            map.put("createTime", now);
//            contentMapper.addThumbUp(map);
//        }else {
//            // 修改
//            map.put("updateTime", now);
//            contentMapper.updateThumbUp(map);
//        }
//        return contentDto.getThumbUpStatus();
    }


}
