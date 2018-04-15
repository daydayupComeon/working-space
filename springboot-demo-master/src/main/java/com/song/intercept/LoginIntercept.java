package com.song.intercept;

import com.song.config.RedisUtils;
import org.codehaus.groovy.util.StringUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * Created by admin on 2018/4/6.
 */
public class LoginIntercept implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //获取请求的URL
        String url = httpServletRequest.getRequestURI();
        //URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制
        if(url.indexOf("login")>=0){
            return true;
        }
        //获取Session
        HttpSession session = httpServletRequest.getSession();
        Object username = session.getAttribute("username");

        if(username != null){
            String sessionId2 = (String) redisUtils.get("username2");
            if(sessionId2 != null && !StringUtils.isEmpty(sessionId2)){
                String sessionId = (String) redisUtils.get("username");
                Long loginTime = Long.parseLong((String) redisUtils.get(sessionId));

                Long loginTime2 = Long.parseLong((String) redisUtils.get(sessionId2));
                if(loginTime < loginTime2){

                }
            }
            return true;
        }

        //不符合条件的，跳转到登录界面
        httpServletRequest.getRequestDispatcher("登录页面").forward(httpServletRequest, httpServletResponse);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
