package com.thank.cuttlefish.content.task;

import com.thank.cuttlefish.common.constant.CuttlefishConstant;
import com.thank.cuttlefish.content.mapper.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * cron表达式：秒 分 时 日 月 周
 * @Scheduled
 * 定时任务持久化redis缓存
 */
@Component
@Configuration
@EnableScheduling
public class ContentScheduledTask {

    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    // 点赞持久化
    @Scheduled(cron = "每隔x秒执行一次：*/59 */1 * * * ? ")
    private void contentThumbPersist(){
        System.out.println("----定时任务持久化：点赞start----" );
        this.contentThumbUpPersist();
        this.contentThumbUpCancelPersist();
        System.out.println("----定时任务持久化：点赞end----" );
    }

    // 点赞
    private void contentThumbUpPersist(){
        Map<String, Object> cacheMap = new HashMap<>();
        String thumbUpPrefix = CuttlefishConstant.REDIS_KEY_USER_THUMBUP_PREFIX + "*";
        Set<String> cacheThumbUpData = redisTemplate.keys(thumbUpPrefix);
        if (cacheThumbUpData.size() == 0) {
            return;
        }
        for (String key: cacheThumbUpData){
            Date now = new Date();
            Set<String> values = redisTemplate.opsForSet().members(key);
            cacheMap.put("contentId", Integer.parseInt(key.replace(CuttlefishConstant.REDIS_KEY_USER_THUMBUP_PREFIX, "")));
            for (String userId: values){
                cacheMap.put("userId", Integer.parseInt(userId));
                Integer count = contentMapper.isThumbUpExist(cacheMap);
                if (count == 0){
                    // 新增
                    cacheMap.put("createTime", now);
                    contentMapper.addThumbUp(cacheMap);
                }else {
                    // 修改
                    cacheMap.put("thumbUpStatus", 1);
                    cacheMap.put("updateTime", now);
                    contentMapper.updateThumbUp(cacheMap);
                }
            }
            redisTemplate.delete(key);
        }
    }


    // 取消点赞
    private void contentThumbUpCancelPersist(){
        Map<String, Object> cacheMap = new HashMap<>();
        String thumbUpPrefix = CuttlefishConstant.REDIS_KEY_USER_THUMBUP_CANCEL_PREFIX + "*";
        Set<String> cacheThumbUpCancelData = redisTemplate.keys(thumbUpPrefix);
        if (cacheThumbUpCancelData.size() == 0) {
            return;
        }
        for (String key: cacheThumbUpCancelData){
            Date now = new Date();
            Set<String> values = redisTemplate.opsForSet().members(key);
            cacheMap.put("contentId", Integer.parseInt(key.replace(CuttlefishConstant.REDIS_KEY_USER_THUMBUP_CANCEL_PREFIX, "")));
            for (String userId: values){
                cacheMap.put("userId", Integer.parseInt(userId));
                cacheMap.put("updateTime", now);
                // 点赞取消
                cacheMap.put("thumbUpStatus", 0);
                contentMapper.updateThumbUp(cacheMap);
            }
            redisTemplate.delete(key);
        }
    }



}
