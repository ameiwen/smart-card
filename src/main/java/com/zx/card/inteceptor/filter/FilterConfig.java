package com.zx.card.inteceptor.filter;


import com.zx.card.inteceptor.wrapper.GlobalFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registerGlobalFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new GlobalFilter());
        registration.addUrlPatterns("/*");
        registration.setName("globalFilter");
        registration.setOrder(1);//如果是多个过滤器 数字越小优先级越高
        return registration;
    }

}
