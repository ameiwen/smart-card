package com.zx.card.config;

import com.zx.card.inteceptor.BaseInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 加载这个bean主要用于MyInterceptor中可以注入service或者dao
     * @return
     */
    @Bean
    public BaseInterceptor getMyInterceptor(){
        return new BaseInterceptor();
    }

    /**
     * 配置静态访问资源 相当于 springmvc中
     * <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态资源
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    /**
     * 以前需要先创建个Controller控制类，在写方法跳转到页面
     * 配置后直接访问http://localhost:8080/到index.html页面了
     * @param registry
     */
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        super.addViewControllers(registry);
    }

    /**
     * 拦截器，用于配置拦截规则
     * 相当于 <mvc:interceptor>******</mvc:interceptor>
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMyInterceptor())
                .addPathPatterns("/users/**")
                .addPathPatterns("/order/**")
                .addPathPatterns("/bank/**");
        super.addInterceptors(registry);
    }
}
