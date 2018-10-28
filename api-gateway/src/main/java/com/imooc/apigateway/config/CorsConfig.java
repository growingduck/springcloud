package com.imooc.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * 跨域配置
 * 通常跨域是在被请求的类或接口上添加注解@CrossOrigin(),,接口太多显然不合理，所有在zuul中统一配置
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); //允许cookie跨域
        config.setAllowedHeaders(Arrays.asList("*")); //允许的头
        config.setAllowedMethods(Arrays.asList("*")); //允许的方法，如GET,POST
        config.setAllowedOrigins(Arrays.asList("*")); //允许的域名，如http://www.a.com
        config.setMaxAge(300L);  //300s的时间内，同样的跨域请求不再检查
        source.registerCorsConfiguration("/**",config);
        return new CorsFilter(source);
    }

}
