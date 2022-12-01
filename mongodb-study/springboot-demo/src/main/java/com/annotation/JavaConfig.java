package com.annotation;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@ComponentScan
/***
 * @Import 三种用法
 * 1.导入指定类
 * 2.如果导入类实现了ImportSelector接口，则不会注入该类，会执行selectImports方法，并将方法返回类注入容器
 * 3.如果导入类实现类{@link ImportBeanDefinitionRegistrar}
 */
//@Import(UserService.class)
@Import(MyImportSelector.class)
public class JavaConfig {
}
