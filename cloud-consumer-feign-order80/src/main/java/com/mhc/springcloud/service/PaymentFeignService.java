package com.mhc.springcloud.service;

import com.mhc.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @FeignClient  通过这种方式直接通过接口来实现服务调用以及负载，相当于调到了CLOUD-PAYMENT-SERVICE服务下的/payment/get/{id}地址
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentId(@PathVariable("id") Long id);
}
