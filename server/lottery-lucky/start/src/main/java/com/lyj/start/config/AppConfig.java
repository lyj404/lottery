package com.lyj.start.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author lyj
 * @date 2023-07-11
 **/
@Configuration
@EnableScheduling
@EnableFeignClients(basePackages = "com.lyj.lotterclient.feign")
@EnableTransactionManagement
@ComponentScan("com.lyj")
@MapperScan(basePackages = "com.lyj.lotterinfrastructure.gateway.impl.mapper")
public class AppConfig {
}
