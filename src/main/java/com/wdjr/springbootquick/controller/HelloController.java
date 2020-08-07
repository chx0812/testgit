package com.wdjr.springbootquick.controller;

import com.wdjr.springbootquick.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//这个类的所有方法返回的数据直接返回给浏览器（如果是对象转为json数据）
/*@ResponseBody
@Controller*/
@RestController
public class HelloController extends BaseController {


    @GetMapping("/login")
    public Object login(@RequestParam("name") String name,
                        @RequestParam("pwd") String pwd) {

        Map<String, Object> map = new HashMap<>(8);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, pwd);
        try {
            //下一步到Realm中认证
            subject.login(token);
            map.put("msg", "success");
            map.put("code", 0);
            map.put("token", subject.getSession().getId());
        } catch (UnknownAccountException e) {
            map.put("msg", "用户不存在");
            map.put("code", 1);
        }catch (IncorrectCredentialsException e){
            map.put("msg", "密码错误");
            map.put("code", 1);
        }
        return map;
    }

    @GetMapping("/user/add")
    @RequiresPermissions("user:add")
    public Object add() {
        Map<String, Object> map = new HashMap<>(8);
        map.put("msg", "success");
        map.put("code", 0);
        return map;
    }
    @GetMapping("/user/delete")
    @RequiresPermissions("user:delete")
    public Object delete() {
        Map<String, Object> map = new HashMap<>(8);
        map.put("msg", "success");
        map.put("code", 0);
        return map;
    }



}