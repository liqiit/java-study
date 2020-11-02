package com.liqi.configuration;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

/**
 * Title: CustomPing
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/7
 */
public class CustomPing implements IPing {
    @Override
    public boolean isAlive(Server server) {
        return false;
    }
}
