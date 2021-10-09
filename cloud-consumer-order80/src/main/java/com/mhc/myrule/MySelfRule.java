package com.mhc.myrule;


import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MA
 * 用于自定义轮询机制，并且该包要单独与启动类所在的包分隔开
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule() {
        //随机
        return new RandomRule();
    }
}
