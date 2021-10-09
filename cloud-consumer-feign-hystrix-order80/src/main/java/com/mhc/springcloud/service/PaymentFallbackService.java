package com.mhc.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author MA
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfoOK(Integer id) {
        return "fall back ";
    }

    @Override
    public String paymentInfoTimeOut(Integer id) {
        return "fall back ";
    }
}
