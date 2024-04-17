package com.lyj.gateway;

import com.lyj.gateway.filter.JwtTokenGlobalFilter;
import com.lyj.gateway.resolver.IpKeyResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({JwtTokenGlobalFilter.class})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean("ipKeyResolver")
    public IpKeyResolver ipKeyResolver() {
        return new IpKeyResolver();
    }

}
