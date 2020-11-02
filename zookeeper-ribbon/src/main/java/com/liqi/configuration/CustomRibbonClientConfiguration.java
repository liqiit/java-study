package com.liqi.configuration;

import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * Title: CustomRibbonClientConfiguration
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/7
 */
public class CustomRibbonClientConfiguration  extends RibbonClientConfiguration {
    @Bean
    @ConditionalOnMissingBean
    @Override
    public IClientConfig ribbonClientConfig() {
        IClientConfig config = super.ribbonClientConfig();
        //可以增加一些配置，例如Ping后台主机的时间间隔5s
        config.set(CommonClientConfigKey.NFLoadBalancerPingInterval, 5);
        //可以在此设置Ping实现类（无需重写ribbonPing，base中会根据如下配置反射构造IPing），也可以使用方法直接构造，例如下面的ribbonPing()方法
        config.set(CommonClientConfigKey.NFLoadBalancerPingClassName,"com.wpstan.custom.ribbon.CustomPing");
        //设置MaxAutoRetries为2，确保某个服务挂的瞬间，ServerStats的Successive Connection Failure大于或等于
        //ServerStats中的connectionFailureThreshold的默认次数3。这样ServerStats的isCircuitBreakerTripped才被置位短路。
        //在Retry重试的时候，CustomRule中Choose下一个服务的时候，确保之前的服务已经短路，选用下一个服务，否则可能继续选择不可用服务导致报错。
        //还有一种方式就是调小ServerStats的connectionFailureThreshold。
        config.set(CommonClientConfigKey.MaxAutoRetries, 2);
        return config;
    }

    @Bean
    @ConditionalOnMissingBean
    @Override
    public ServerList<Server> ribbonServerList(IClientConfig config) {
        CustomServerList serverList = new CustomServerList();
        serverList.initWithNiwsConfig(config);
        return serverList;
    }

    @Bean
    @ConditionalOnMissingBean
    @Override
    public IRule ribbonRule(IClientConfig config) {
        CustomRule rule = new CustomRule();
        rule.initWithNiwsConfig(config);
        return rule;
    }

    @Bean
    @ConditionalOnMissingBean
    @Override
    public IPing ribbonPing(IClientConfig config) {
        return new CustomPing();
    }
}
