package com.liqi.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

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
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init");
    }
}
