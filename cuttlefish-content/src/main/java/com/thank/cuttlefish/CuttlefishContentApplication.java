package com.thank.cuttlefish;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * 内容服务
 */
@SpringCloudApplication
@EnableFeignClients("com.thank.cuttlefish.*.api")
@ComponentScan(basePackages = {"com.thank.cuttlefish.base.config", "com.thank.cuttlefish.content"})
@MapperScan(basePackages = "com.thank.cuttlefish.content.mapper")
public class CuttlefishContentApplication {
	public static void main(String[] args) {
		SpringApplication.run(CuttlefishContentApplication.class, args);
	}
}
