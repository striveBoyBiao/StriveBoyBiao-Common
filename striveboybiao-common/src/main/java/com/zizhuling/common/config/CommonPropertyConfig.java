package com.zizhuling.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <P>
 * 描述：配置common中的propertie
 * </p>
 *
 * @author lishang Created on 2020/6/2 16:22
 * @version 1.0
 */
@Configuration
@PropertySource(value = "classpath:META-INF/prop/common.properties")
public class CommonPropertyConfig {


}
