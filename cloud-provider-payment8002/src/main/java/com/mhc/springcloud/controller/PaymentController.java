package com.mhc.springcloud.controller;

import com.mhc.springcloud.entities.CommonResult;
import com.mhc.springcloud.entities.Payment;
import com.mhc.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author MA
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPost;
    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果", result);
        CommonResult commonResult = new CommonResult();
        if (result > 0) {
            commonResult.setCode(200);
            commonResult.setMessage("插入成功"+"port:"+serverPost);
            commonResult.setData(result);
        } else {
            commonResult.setData(200);
            commonResult.setMessage("插入失败"+"port:"+serverPost);
        }
        return commonResult;
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentId(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentId(id);
        CommonResult commonResult = new CommonResult();
        if (result != null) {
            commonResult.setCode(200);
            commonResult.setMessage("查询成功"+"port:"+serverPost);
            commonResult.setData(result);
        } else {
            commonResult.setData(200);
            commonResult.setMessage("查询失败，id为" + id+"port:"+serverPost);
        }
        return commonResult;
    }
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPost;
    }
}
