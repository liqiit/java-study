package com.liqi.register.controller;

import com.liqi.register.monitor.ServiceMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: ServiceUrlController
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/8/28
 */
@RestController
public class ServiceUrlController {
    @Autowired
    private ServiceMonitor serviceMonitor;

    @GetMapping("/queryURL")
    public String queryURL() {
        return serviceMonitor.getServiceURL();
    }
}
