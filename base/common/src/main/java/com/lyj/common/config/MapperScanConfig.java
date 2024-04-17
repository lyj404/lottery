package com.lyj.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lyj
 * @date 2023-06-30
 **/
@Configuration
@MapperScan("com.lyj.*.mapper")
public class MapperScanConfig {
}
