package com.nacos.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title: ProviderController
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/12/28
 */
@RestController
public class ProviderController {
    @GetMapping("/helloNacos")
    public String helloNacos(){
        return "你好，nacos！";
    }
}
