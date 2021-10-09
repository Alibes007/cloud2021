package com.mhc.springcloud.controller;

import com.mhc.springcloud.entities.CommonResult;
import com.mhc.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author MA
 */
@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult getPaymentId(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentId(id);
    }
}
