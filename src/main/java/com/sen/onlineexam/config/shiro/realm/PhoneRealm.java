package com.sen.onlineexam.config.shiro.realm;

import com.sen.onlineexam.pojo.User;
import com.sen.onlineexam.pojo.UserAuth;
import com.sen.onlineexam.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashSet;
import java.util.Set;

public class PhoneRealm extends AuthorizingRealm {
    public void setName(String name) {
        super.setName("phoneRealm");
    }

    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        roles.add(user.getRole().getRoleName());
        info.setRoles(roles);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        String phone = userToken.getUsername();
        UserAuth userAuth = userService.login(phone);
        if (userAuth != null) {
            User user = userAuth.getUser();
            String realCode = redisTemplate.opsForValue().get(phone);
            return new SimpleAuthenticationInfo(user, realCode, this.getName());
        }
        return null;
    }
}
