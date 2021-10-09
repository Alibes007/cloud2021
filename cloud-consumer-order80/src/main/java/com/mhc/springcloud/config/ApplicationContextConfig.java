package com.mhc.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author MA
 */
@Configuration
public class ApplicationContextConfig {

    /**
     * @LoadBalanced 开启负载均衡 默认是轮询机制
     * @return
     */

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
