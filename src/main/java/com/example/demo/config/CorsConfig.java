package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA
 *  配置全局跨域  使得layui的请求能够接收
 * @author lqh
 * @date 2021/12/04/10:46
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //corsConfiguration.addAllowedOrigin("*"); //允许任何域名
        // SpringBoot 2.4.6以上会有这个方法
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.addAllowedHeader("*"); //允许任何请求头
        corsConfiguration.addAllowedMethod("*"); //允许任何方法
        corsConfiguration.setMaxAge(3600L); //预检请求的有效期，单位为秒。
        corsConfiguration.setAllowCredentials(true); //是否支持安全证书(必需参数)
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
