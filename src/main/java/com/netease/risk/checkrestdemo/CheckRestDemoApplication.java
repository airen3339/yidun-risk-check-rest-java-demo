package com.netease.risk.checkrestdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author 花名：天长 坐标：杭州
 * @version 2022-08-05
 * 不推荐启动方式： CheckRestDemoApplication.main()
 * 推荐启动方式： CheckRestDemoApplicationTests.startCheck()
 */
@SpringBootApplication
public class CheckRestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckRestDemoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		return new RestTemplate(factory);
	}

	@Bean
	public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(3000);
		factory.setConnectTimeout(5000);
		return factory;
	}
}
