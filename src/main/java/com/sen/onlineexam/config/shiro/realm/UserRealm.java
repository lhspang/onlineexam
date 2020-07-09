package com.sen.onlineexam.config.shiro.realm;

import com.sen.onlineexam.pojo.User;
import com.sen.onlineexam.pojo.UserAuth;
import com.sen.onlineexam.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    public void setName(String name) {
        super.setName("userRealm");
    }

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        roles.add(user.getRole().getRoleName());
        info.setRoles(roles);
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        String account = userToken.getUsername();
        UserAuth userAuth = userService.login(account);
        if (userAuth != null) {
            User user = userAuth.getUser();
            ByteSource credentialsSalt = ByteSource.Util.bytes(userAuth.getSalt());
            return new SimpleAuthenticationInfo(user, userAuth.getCredential(), credentialsSalt, this.getName());
        }
        return null;
    }
}
