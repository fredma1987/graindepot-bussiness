package com.zhoubi.graindepot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan(value = "com.zhoubi.graindepot.mapper")
@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE,maxInactiveIntervalInSeconds=4*60*60)
public class GraindepotBussinessApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GraindepotBussinessApplication.class, args);
	}

}

