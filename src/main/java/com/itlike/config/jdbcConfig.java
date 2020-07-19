package com.itlike.config;

import com.itlike.interceptors.myInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class jdbcConfig implements WebMvcConfigurer {

//    @Bean
//    @ConfigurationProperties(prefix = "jdbc")
//    public DataSource dataSource(){
//        DruidDataSource dataSource = new DruidDataSource();
//        return dataSource;
//    }

    //设置请求路径
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("login");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有请求   myinterceptor拦截后的操作
        registry.addInterceptor(new myInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/login","/userLogin");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }


}
