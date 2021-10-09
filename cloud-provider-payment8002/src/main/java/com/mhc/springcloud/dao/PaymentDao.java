package com.mhc.springcloud.dao;

import com.mhc.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author MA
 */
@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentId(@Param("id") Long id);
}
