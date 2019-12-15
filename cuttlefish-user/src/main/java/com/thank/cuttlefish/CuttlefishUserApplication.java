package com.thank.cuttlefish;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * 用户服务
 */
@SpringCloudApplication
@EnableFeignClients("com.thank.cuttlefish.*.api")
@ComponentScan(value = "com.thank.cuttlefish")
@MapperScan(basePackages = "com.thank.cuttlefish.*.mapper")
public class CuttlefishUserApplication {
	public static void main(String[] args) {
		SpringApplication.run(CuttlefishUserApplication.class, args);
	}
}
