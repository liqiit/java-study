package com.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Title: FirstStepTest
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/13
 */
public class FirstStepTest {
    @Test
    public void test() {

        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("liqi", "liqi");
            token.setRememberMe(true);
            try {
                subject.login(token);
            } catch (UnknownAccountException e) {
                e.printStackTrace();
            } catch (IncorrectCredentialsException e) {
                e.printStackTrace();
            } catch (LockedAccountException e) {
                e.printStackTrace();
            } catch (AuthenticationException ae) {
                ae.printStackTrace();
            }
        }
        if (subject.hasRole("admin2")) {
            System.out.println("liqi 属于角色admin2");
        }
        if(subject.isPermitted("user:delete")){
            System.out.println("可以查询用户");
        }
        System.out.println("isAuthenticated:" + subject.isAuthenticated());
    }
}
