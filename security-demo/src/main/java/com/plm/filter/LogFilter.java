package com.plm.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @author : cwh
 * 2019/2/22 0022
 * description ：
 */
// @Component
public class LogFilter/* implements Filter*/ {
   /* @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("log filter start");
        long start = new Date().getTime();
        chain.doFilter(request,response);
        System.out.println("time"+":"+ (new Date().getTime() - start));
        System.out.println("log filter finish");

    }

    @Override
    public void destroy() {
        System.out.println("log filter destroy");
    }*/
}
