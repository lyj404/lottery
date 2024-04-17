package com.lyj.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan(basePackages = {"com.lyj.common", "com.lyj.config"})
public class BaseCommonAutoConfigure {

}
