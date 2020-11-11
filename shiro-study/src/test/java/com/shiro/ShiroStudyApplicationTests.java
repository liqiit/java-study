package com.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShiroStudyApplicationTests {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @BeforeEach
    public void addUer() {
        simpleAccountRealm.addAccount("liqi", "1234");

    }

    @Test
    public void test() {
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("liqi", "1234");
        subject.login(token);
        System.out.println("isAuthenticated:" + subject.isAuthenticated());


    }

}
