package com.thank.cuttlefish.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 微信服务
 */
@SpringCloudApplication
@EnableFeignClients("com.thank.cuttlefish.*.api")
@ComponentScan(value = "com.thank.cuttlefish")
public class CuttlefishWechatApplication {

	public static void main(String[] args) {
		SpringApplication.run(CuttlefishWechatApplication.class, args);
	}
}
