package com.lz.applet.config;

import com.lz.applet.entity.User;
import com.lz.applet.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义的UserRealm
 *
 * @author dell
 * @date 2020-06-10 21:29
 */
public class UserRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService;

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行了授权方法doGetAuthorizationInfo");

        //给用户授权 SimpleAuthorizationInfo
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //给用户都授权了这个权限
        info.addStringPermission("user:user");

        //拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        //拿到user对象
        User currentUser = (User) subject.getPrincipal();

        //设置当前用户的权限
        if (null != currentUser.getPerms() && currentUser.getPerms() != "") {
            info.addStringPermission(currentUser.getPerms());
        }
        return info;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证方法doGetAuthenticationInfo");
//        //用户名 密码  数据库中取   模拟
//        String name = "root";
//        String password = "123456";

        //通过获取到用户信息
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        //连接真实的数据库, 根据用户的登录信息取数据库查找
        User userInfo = userService.queryUserByName(userToken.getUsername());

        //如果没有这个人
        if (null == userInfo) {
            return null;
        }

        //密码认证shiro做
        return new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), "");
    }
}
