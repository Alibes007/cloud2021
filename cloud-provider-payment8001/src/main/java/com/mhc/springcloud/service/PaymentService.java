package com.mhc.springcloud.service;

import com.mhc.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;



public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentId(@Param("id") Long id);
}
