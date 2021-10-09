package com.mhc.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MA
 */
@Configuration
public class GateWayConfig {
    /**
     * 路由构造器
     *
     * @param routeLocatorBuilder
     * @return
     */
    /**
     * 当访问localhost：9527的时候会自动转发到http://news.baidu.con/guonei，
     * 注意转发跟重定向的区别（这里实际操作出来是重定向？）
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_mhc",
                r -> r.path("/32455698").uri("http://space.bilibili.com/32455698")).build();
        return routes.build();
    }
}
