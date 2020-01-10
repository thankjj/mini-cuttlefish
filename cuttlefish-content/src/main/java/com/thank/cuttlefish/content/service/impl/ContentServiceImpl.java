package com.thank.cuttlefish.content.service.impl;

import com.thank.cuttlefish.base.service.impl.MyServiceImpl;
import com.thank.cuttlefish.base.utils.WebUtil;
import com.thank.cuttlefish.common.constant.CuttlefishRedisConstant;
import com.thank.cuttlefish.content.mapper.ContentMapper;
import com.thank.cuttlefish.content.service.ContentService;
import com.thank.cuttlefish.dto.ContentDto;
import com.thank.cuttlefish.pojo.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
        String currentIP = WebUtil.getInstance().getIpAddress(); // 当前IP
        String contentViewCountKey = CuttlefishRedisConstant.REDIS_KEY_CONTENT_VIEW_COUNT_PREFIX + contentDto.getId(); // 当前文章浏览总数key

        // 约定时间内同一用户，同一IP再次浏览
        String contentViewRecordKey = CuttlefishRedisConstant.REDIS_KEY_CONTENT_VIEW_RECORD_PREFIX + contentDto.getAuthorId() +  "_" + currentIP;
        String contentViewRecordValue = contentDto.getId() + "";
        if (!redisTemplate.hasKey(contentViewRecordKey)){
            redisTemplate.opsForValue().set(contentViewRecordKey, contentViewRecordValue);
            redisTemplate.expire(contentViewRecordKey, CuttlefishRedisConstant.REDIS_KEY_CONTENT_VIEW_RECORD_TIMEOUT, TimeUnit.SECONDS);
        }else {
            return Integer.parseInt(redisTemplate.opsForValue().get(contentViewCountKey).toString());
        }

        Date now = new Date();
        Map<String, Object> map = new HashMap<String, Object>();
        // 新增浏览记录
        map.put("ip", currentIP);
        map.put("userId", contentDto.getAuthorId());
        map.put("contentId", contentDto.getId());
        map.put("createTime", now);
        contentMapper.addViewRecord(map);

        // 文章总浏览数 缓存加1
        if(!redisTemplate.hasKey(contentViewCountKey)){
            redisTemplate.opsForValue().set(contentViewCountKey, 1);
        }else {
            redisTemplate.opsForValue().increment(contentViewCountKey);
        }

        System.out.println(redisTemplate.opsForValue().get(contentViewCountKey));
        return Integer.parseInt(redisTemplate.opsForValue().get(contentViewCountKey).toString());

    }

    @Override
    public Integer addOrUpdateThumbUp(ContentDto contentDto) {
        Integer thumbStatus = contentDto.getThumbUpStatus();
        String cacheKeyPrefix = thumbStatus == 0 ? CuttlefishRedisConstant.REDIS_KEY_USER_THUMBUP_CANCEL_PREFIX : CuttlefishRedisConstant.REDIS_KEY_USER_THUMBUP_PREFIX;
        String cacheCurrentKey = cacheKeyPrefix + contentDto.getId();
        String cacheOppositeKey = (thumbStatus == 1 ? CuttlefishRedisConstant.REDIS_KEY_USER_THUMBUP_CANCEL_PREFIX : CuttlefishRedisConstant.REDIS_KEY_USER_THUMBUP_PREFIX) + contentDto.getId();
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
    }


}
