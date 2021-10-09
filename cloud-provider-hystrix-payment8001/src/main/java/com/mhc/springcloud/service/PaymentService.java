package com.mhc.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfoOK(Integer id) {
        return "线程池" + Thread.currentThread().getName() + "paymentInfo_Ok,id: " + id + "\t" + "";
    }

    /**
     * 服务降级
     * paymentInfoTimeOutHandler方法取兜底
     *
     * @param id
     * @return
     * @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
     * 超出3秒处理时间就报错或者出现其他报错兜底方法都是这一个paymentInfoTimeOutHandler
     */
    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
    public String paymentInfoTimeOut(Integer id) {
        int time = 3;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池" + Thread.currentThread().getName() + "paymentInfoTimeOut,id: " + id + "\t" + "耗时" + time;
    }

    public String paymentInfoTimeOutHandler(Integer id) {
        return "线程池" + Thread.currentThread().getName() + "paymentInfoTimeOutHandler,id: " + id + "\t" + "/(ㄒoㄒ)/~~，系统繁忙";
    }

    //服务熔断
    //=====服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " + id;
    }

}
