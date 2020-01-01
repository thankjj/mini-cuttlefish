package com.thank.cuttlefish;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


/**
 * 用户服务
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("com.thank.cuttlefish.*.api")
@ComponentScan(basePackages = {"com.thank.cuttlefish.base.config.properties", "com.thank.cuttlefish.user"})
@MapperScan(basePackages = "com.thank.cuttlefish.user.mapper")
public class CuttlefishUserApplication {
	public static void main(String[] args) {
		SpringApplication.run(CuttlefishUserApplication.class, args);
	}
}
