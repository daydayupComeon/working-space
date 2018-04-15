package com.song.controller;

import com.song.config.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by admin on 2018/4/6.
 */
@Controller
public class LoginControl {

    @Autowired
    private RedisUtils redisUtils;
    /**
     * 登录
     * @param session
     *   HttpSession
     * @param username
     *   用户名
     * @param password
     *   密码
     * @return
     */
    @RequestMapping(value="/login")
    public String login(HttpSession session, @Param("username") String username, @Param("password") String password, @Param("qzLongin") String qzLogin) throws Exception{
        Object user = session.getAttribute("username");
        if(null != user){
            if(null != qzLogin && "QZLOGIN".equals(qzLogin)){
                String sessionId = session.getId();
                redisUtils.set("username2", sessionId);
                redisUtils.set(sessionId, System.currentTimeMillis());
            }
            return "该用户已经登录，是否强制登录";
        }
        //在Session里保存信息
        session.setAttribute("username", username);
        String sessionId = session.getId();
        redisUtils.set("username", sessionId);
        redisUtils.set(sessionId, System.currentTimeMillis());
        //重定向
        return "redirect:hello.action";
    }

    /**
     * 退出系统
     * @param session
     *   Session
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/logout")
    public String logout(HttpSession session) throws Exception{
        //清除Session
        session.invalidate();

        return "redirect:hello.action";
    }

    public static void main(String args[]) {
        System.out.print(System.currentTimeMillis());
    }
}
