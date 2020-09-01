package com.liqi.controller;

import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: BusinessController
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/1
 */
@RestController
public class BusinessController {
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @RequestMapping("/chooseService")
    public void chooseService() {
        ServiceInstance serviceInstance=loadBalancerClient.choose("zk-client");
        System.out.println(serviceInstance.getHost());
    }
}
