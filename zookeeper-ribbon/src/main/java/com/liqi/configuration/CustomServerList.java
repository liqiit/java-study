package com.liqi.configuration;

import com.netflix.client.IClientConfigAware;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: CustomServerList
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/7
 */
public class CustomServerList implements ServerList<Server>, IClientConfigAware {
    private IClientConfig clientConfig;

    @Override
    public List<Server> getInitialListOfServers() {
        return getUpdatedListOfServers();
    }

    @Override
    public List<Server> getUpdatedListOfServers() {
        List<Server> servers = new ArrayList<>();
        Server server1 = new Server("http://localhost:9000");
        Server server2 = new Server("http://localhost:9001");
        servers.add(server1);
        servers.add(server2);
        return servers;
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        this.clientConfig = iClientConfig;
    }
}
