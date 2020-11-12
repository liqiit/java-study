package com.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * Title: JdbcRealmTest
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/12
 */
public class JdbcRealmTest {
    DruidDataSource druidDataSource=new DruidDataSource();
    {
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3366/shiro?serverTimezone=Asia/Shanghai");
        druidDataSource.setUsername("liqi");
        druidDataSource.setPassword("liqi");
    }
    @Test
    public void test(){
        JdbcRealm jdbcRealm=new JdbcRealm();
        jdbcRealm.setDataSource(druidDataSource);
        jdbcRealm.setPermissionsLookupEnabled(true);
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken();
        token.setPassword("123456".toCharArray());
        token.setUsername("liqi");
        subject.login(token);
        System.out.println("isAuthenticated:"+subject.isAuthenticated());
        subject.checkRole("admin");
        subject.checkPermission("user:update");

    }
}
