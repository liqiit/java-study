package com.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.Ini;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Title: InitRealmTest
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/12
 */
public class InitRealmTest {
    @Test
    public void test(){
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        IniRealm iniRealm=new IniRealm("classpath:user.ini");
        defaultSecurityManager.setRealm(iniRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken();
        token.setPassword("123456".toCharArray());
        token.setUsername("liqi");
        subject.login(token);
        System.out.println("isAuthenticated:"+subject.isAuthenticated());
        subject.checkRole("admin");
        subject.checkPermission("user:delete");

    }
}
