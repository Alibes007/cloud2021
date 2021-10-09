package com.mhc.springcloud.controller;

import com.mhc.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author MA
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoOK(@PathVariable("id") Integer id) {
        String res = paymentHystrixService.paymentInfoOK(id);
        return res;
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
    @HystrixCommand
    public String paymentInfoTimeOut(@PathVariable("id") Integer id) {
        int i = 10/0;
        String res = paymentHystrixService.paymentInfoTimeOut(id);
        return res;
    }

    public String paymentInfoTimeOutHandler(@PathVariable("id") Integer id) {
        return "线程池" + Thread.currentThread().getName() + "paymentInfoTimeOutHandler,id: " + id + "\t" + "消费者80/(ㄒoㄒ)/~~，系统繁忙";
    }
    public String paymentGlobalFallbackMethod(){
        return "通用全局处理的异常，稍后再试";
    }
}
