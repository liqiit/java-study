package com.liqi.nacos.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Title: ConfigController
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/12/28
 */
@Controller
@RefreshScope
@RequestMapping("config")
public class ConfigController {
    @Value(value = "${test.name}")
    private String name;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public String get() {
        return name;
    }
}
