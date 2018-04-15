package com.song.config;

import com.song.intercept.LoginIntercept;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by admin on 2018/4/6.
 */
@Configuration
public class SpringInterceptorRegister extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {

        // 拦截所有路径
        registry.addInterceptor(new LoginIntercept());

        super.addInterceptors(registry);
        }
}
