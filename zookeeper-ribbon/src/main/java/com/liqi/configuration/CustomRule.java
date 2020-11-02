package com.liqi.configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;


/**
 * Title: CustomRule
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/7
 */
public class CustomRule extends AbstractLoadBalancerRule {

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        return null;
    }
}
