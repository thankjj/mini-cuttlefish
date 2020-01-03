package com.thank.cuttlefish.content.task;

import com.thank.cuttlefish.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 打开注解即可测试定时任务，无侵入式，spring集成的定时任务
 */
@Component
@Configuration
@EnableScheduling
public class DemoScheduledTask {

    @Autowired
    private ContentService contentService;

    /**
     * cron表达式：秒 分 时 日 月 周
     * 每隔5秒执行一次
     */
//    @Scheduled(cron = "每隔5秒执行一次：*/5 * * * * ?")
    private void DemoOperate(){
        System.out.println("----定时任务----" + contentService.queryListByRand(null).get(0).getContent());
    }

}
