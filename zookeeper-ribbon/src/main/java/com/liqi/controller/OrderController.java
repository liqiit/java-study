package com.liqi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @RequestMapping("/orders")
    public void queryAll() {
        System.out.println("查询订单");
    }
}
