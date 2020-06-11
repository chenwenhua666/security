package com.plm.config;

import com.plm.filter.LogFilter;
import com.plm.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : cwh
 * 2019/2/22 0022
 * description ：
 */
// @Configuration
public class FilterConfig /*extends WebMvcConfigurerAdapter*/ {

    /*@Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.registerCallableInterceptors();
    }*/

    /*@Autowired
    private LogInterceptor logInterceptor;

    // 1 、interceptor 继承WebMvcConfigurerAdapter
    // 2、重写addInterceptors
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logInterceptor);
    }*/

    /*
    // 如果过滤器没加@Component注解，使用以下bean
    @Bean
    public FilterRegistrationBean getFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        LogFilter logFilter = new LogFilter();
        registrationBean.setFilter(logFilter);
        List<String> urls = new ArrayList<>();
        urls.ad3d("/*");
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }*/


}
