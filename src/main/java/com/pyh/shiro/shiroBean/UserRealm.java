package com.pyh.shiro.shiroBean;

import com.pyh.shiro.mapper.UserMapper;
import com.pyh.shiro.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String  userName = (String) SecurityUtils.getSubject().getPrincipal();
        authorizationInfo.addStringPermission(userMapper.selectUserByName(userName).getPerms());
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("userMapper:"+userMapper);
        String submitName= (String) authenticationToken.getPrincipal();
        User user = userMapper.selectUserByName(submitName);
        return new SimpleAuthenticationInfo(user.getName(),user.getPassword(),getName());
    }
}
