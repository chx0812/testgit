package com.wdjr.springbootquick.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BaseController {
    /**
     * 权限异常
     */
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public Object authorizationException(HttpServletRequest request, HttpServletResponse response) {
        // 输出JSON
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",401);
        map.put("msg","暂无权限");
        return map;
    }
}
