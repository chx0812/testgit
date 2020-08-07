package com.wdjr.springbootquick.util;

import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 跨域请求filter
 * @author 作者：czy
 * @version 版本号:v1.0
 * 项目名称:${PROJECT_NAME}
 * 文件名称:${FILE_NAME}
 * 描述: 一个管理项目各个模块的项目
 * 创建时间: ${DATE}/${TIME}
 * 公司信息:
 * @update [序列:1][日期:2019-3-29] [更改人:$蔡宏旭]
 */
@Configuration
public class CrossDomainFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*
         * Access-Control-Allow-Methods: 真实请求允许的方法
         * Access-Control-Allow-Headers: 服务器允许使用的字段
         * Access-Control-Allow-Credentials: 是否允许用户发送、处理 cookie
         * Access-Control-Max-Age: 预检请求的有效期，单位为秒。有效期内，不会重复发送预检请求
         * */
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*,token");
        String method = request.getMethod();
        if(method.equalsIgnoreCase("OPTIONS")){
            servletResponse.getOutputStream().write("Success".getBytes("utf-8"));
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
