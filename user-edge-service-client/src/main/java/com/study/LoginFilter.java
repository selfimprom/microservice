package com.study;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.study.thrift.user.dto.UserDTO;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract  class LoginFilter implements Filter {

    private static Cache<String,UserDTO> cache =
            CacheBuilder.newBuilder().maximumSize(10000)
            .expireAfterWrite(3, TimeUnit.MINUTES).build();

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getParameter("token");
        if (StringUtils.isBlank(token)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("token")) {
                        token = cookie.getValue();
                    }
                }
            }
        }
        UserDTO userDTO = null;
        if (StringUtils.isNotBlank(token)) {
            userDTO= cache.getIfPresent(token);
            if (userDTO==null){
                userDTO = requestUserInfo(token);
                cache.put(token,userDTO);
            }

        }
        if (userDTO == null) {
            response.sendRedirect("http://localhost:8080/user/login");
            return;
        }



        login(request,response,userDTO);
        filterChain.doFilter(request, response);
    }

    protected abstract void login(HttpServletRequest request, HttpServletResponse response,UserDTO userDTO);
    private UserDTO requestUserInfo(String token) {
//        String url="http://juejohn.com/authentication";
//        HttpClient httpClient = new HttpClient();// 客户端实例化
//
//        HttpPost httpPost = new HttpPost(url);
//        httpPost.addHeader("token",token);
//        httpClient.execute(httpPost);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(2);
        userDTO.setMobile("18109089307");
        userDTO.setUsername("test");
        userDTO.setPassword("098f6bcd4621d373cade4e832627b4f6");
        return userDTO;
    }

    public void destroy() {

    }
}
