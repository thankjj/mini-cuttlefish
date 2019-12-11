package com.thank.cuttlefish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
 * 配置简单EurekaServer，本处使用单机版本
 */
@SpringBootApplication
@EnableEurekaServer
public class CuttlefishEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuttlefishEurekaServerApplication.class, args);
	}
}
