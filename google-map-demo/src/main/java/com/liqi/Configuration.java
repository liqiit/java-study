package com.liqi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Title: Configuration
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/24
 */
@org.springframework.context.annotation.Configuration
public class Configuration {
    /***
     * 如果占位符不存在直接抛异常，默认如果不能解析占位符名称当成值注入
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
