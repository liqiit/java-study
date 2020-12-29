package com.liqi.nacos.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
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
    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }
}
