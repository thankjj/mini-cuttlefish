package com.thank.cuttlefish.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 微信服务
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("com.thank.cuttlefish.*.api")
//@ComponentScan(value = "com.thank.cuttlefish.wechat")
@ComponentScan(basePackages = {"com.thank.cuttlefish.*.fallback", "com.thank.cuttlefish.wechat"})
public class CuttlefishWechatApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuttlefishWechatApplication.class, args);
	}
}
