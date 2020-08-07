package com.wdjr.springbootquick.config;

import com.wdjr.springbootquick.entity.User;
import com.wdjr.springbootquick.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RealmConfig  extends AuthorizingRealm {


    @Autowired
    private UserMapper userMapper;

    /**
     * 用户验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("用户认证");
        // 加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (token.getPrincipal() == null) {
            return null;
        }
        // 获取用户信息
        UsernamePasswordToken upt = (UsernamePasswordToken) token;
        // 根据token获取用户名
        String username = upt.getUsername();
        // 往数据库中查询用户
        User user = userMapper.selectByUserName(username);
        if (user == null) {
            throw new UnknownAccountException();
        }else{
                SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                        username,
                        //密码
                        user.getPwd(),
                        //ByteSource.Util.bytes(user.getCredentialsSalt()),// 盐
                        //getName()
                        getName()
                );
                return authenticationInfo;
        }
    }

    /**
     * 用户授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权");
        //获取认证时候添加到SimpleAuthenticationInfo中的实例
        String  username = (String) principals.getPrimaryPrincipal();
        User user = userMapper.selectByUserName(username);
        List<String> roleList = new ArrayList<>();
        roleList.add("admin");
        roleList.add("test");
        List<String> authList = new ArrayList<>();
        authList.add("user:update");
        authList.add("user:delete");
        authList.add("event:query");
        user.setRoleList(roleList);// user类中有属性 List<String> roleList
        user.setAuthList(authList);// user类中有属性 List<String> authList
        // 将 List 转化为 Set,并实现去重
        Set<String> roleSet = new HashSet<>(user.getRoleList());
        Set<String> authSet = new HashSet<>(user.getAuthList());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(authSet);
        return authorizationInfo;
    }

}
