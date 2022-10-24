package com.jjlee.oauth.external.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "com.deploy.playtogether")
@Configuration
public class FeignClientConfig {
}
