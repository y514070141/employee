package com.itlike.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class myInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器");
        //获取user对象
        Object user = request.getSession().getAttribute("loginUser");
        if(user==null){
            //未登录
            request.setAttribute("msg","没有登录权限");
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        }
        //已登录 放行
        return true;
    }
}
