package com.shiro.auth;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

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
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, "91d33f11fb24e6a47b9255a85ae98c07", getName());
        //md5加盐
        simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("liqi"));
        return simpleAuthenticationInfo;
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

    public static void main(String[] args) {
        Md5Hash md5Hash=new Md5Hash("123456","liqi");
        System.out.println(md5Hash.toString());
    }
}
