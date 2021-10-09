package com.mhc.springcloud.service.impl;

import com.mhc.springcloud.dao.PaymentDao;
import com.mhc.springcloud.entities.Payment;
import com.mhc.springcloud.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author MA
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;


    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }


    @Override
    public Payment getPaymentId(Long id) {
        return paymentDao.getPaymentId(id);
    }
}
