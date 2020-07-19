package com.itlike.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;

@Configuration
public class druidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource(){
        return new DruidDataSource();
    }


    //1.配置servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        HashMap<Object, Object> hashMap = new HashMap<>();//ResourceServlet.class
        hashMap.put("loginUsername","admin");
        hashMap.put("loginPassword","1234");
        hashMap.put("allow","");//允许访问所有
        bean.setInitParameters(hashMap);//初始化druid的参数
        return bean;
    }
    //2配置过滤器
    @Bean
    public FilterRegistrationBean webStatServlet(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        HashMap<Object, Object> hashMap = new HashMap<>();//webStatFIlter
        hashMap.put("exclusions","*.css,*.js,/druid/*");//不需要拦截的路径请求   css js 上面访问druid的路径
        bean.setInitParameters(hashMap);
        bean.setUrlPatterns(Arrays.asList("/*"));//设置拦截的路径
        return bean;
    }

}
