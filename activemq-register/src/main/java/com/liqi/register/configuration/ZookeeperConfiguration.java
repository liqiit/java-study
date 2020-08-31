package com.liqi.register.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Title: ZookeeperConfiguration
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/8/28
 */
@Configuration
public class ZookeeperConfiguration {
    @Value("${zookeeper.server}")
    public String zookeeperServer;
    @Value(("${zookeeper.sessionTimeoutMs}"))
    public int sessionTimeoutMs;
    @Value("${zookeeper.connectionTimeoutMs}")
    public int connectionTimeoutMs;
    @Value("${zookeeper.maxRetries}")
    public int maxRetries;
    @Value("${zookeeper.baseSleepTimeMs}")
    public int baseSleepTimeMs;

}
