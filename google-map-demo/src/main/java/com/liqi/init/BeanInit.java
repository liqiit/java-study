package com.liqi.init;

import com.liqi.configuration.SystemConfig;
import com.liqi.pojo.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.util.Properties;

/**
 * Title: BeanInit
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/23
 */
@Component
public class BeanInit implements InitializingBean {
    @Autowired
    SystemConfig systemConfig;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(systemConfig.name);
//        Resource resource = new ClassPathResource("system.properties");
//        Properties properties = new Properties();
//        properties.load(resource.getInputStream());
//        System.out.println(properties.get("name"));

    }
}
