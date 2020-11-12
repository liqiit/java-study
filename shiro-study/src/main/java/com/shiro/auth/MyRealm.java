package com.shiro.auth;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        String userName=(String)principalCollection.getPrimaryPrincipal();

        simpleAuthorizationInfo.setRoles(getRolesByUserName(userName));
        simpleAuthorizationInfo.setStringPermissions(getPermissionsByUserName(userName));
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        if (!username.equalsIgnoreCase("liqi")) {
            throw new UnknownAccountException("账户不存在");
        }
        return new SimpleAuthenticationInfo(username, "123456", getName());
    }

    private Set<String>getRolesByUserName(String userName){
        Set<String>roles=new HashSet<>();
        roles.add("admin");
        roles.add("user");
        return roles;
    }
    private Set<String>getPermissionsByUserName(String userName){
        Set<String>permissions=new HashSet<>();
        permissions.add("user:delete");
        return permissions;
    }
}
