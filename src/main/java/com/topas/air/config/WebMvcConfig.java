package com.topas.air.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.topas.air.interceptor.JwtTokenInterceptor;
import com.topas.air.interceptor.PageInterceptor;

import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {

    @Value("${spring.url}")
    private static String URI;

//    private final PageInterceptor pageInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenInterceptor())
                .excludePathPatterns(URI + "/swagger-resources/**", URI + "/swagger-ui/**", URI + "/v3/api-docs", URI + "/api-docs/**")
                .excludePathPatterns("/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs", "/api-docs/**")
                .excludePathPatterns("/signUp", "/signIn", "/error/**", "/reissue")
//                .excludePathPatterns("/**/paging")
                .addPathPatterns("/**");

        registry.addInterceptor(pageInterceptor())
        .addPathPatterns("/**/paging");

    }

    @Bean
    public JwtTokenInterceptor jwtTokenInterceptor(){
        return new JwtTokenInterceptor();
    }
    @Bean
    public PageInterceptor pageInterceptor(){
        return new PageInterceptor();
    }
}
