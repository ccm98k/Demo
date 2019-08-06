package com.zyrj.usermanagement.config;

import com.zyrj.usermanagement.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther ccm
 * @date 18:41
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List list=new ArrayList();
        list.add("/login");
        list.add("/sign_in");
        list.add("/**/*.css");
        list.add("/**/*.js");
        list.add("/forgotPassword");
        list.add("/join");
        list.add("/FogotcheckName");
        list.add("/checkName");
        list.add("/api/user");

        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(list);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }
}
