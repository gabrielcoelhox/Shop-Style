package com.shopstyle.msgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class MsGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder
					.routes()
						.route(r -> r.path("/v1/customers/**").uri("lb://ms-customer"))
						.route(r -> r.path("/v1/address/**").uri("lb://ms-customer"))
						.route(r -> r.path("/v1/categories/**").uri("lb://ms-catalog"))
						.route(r -> r.path("/v1/products/**").uri("lb://ms-catalog"))
						.route(r -> r.path("/v1/skus/**").uri("lb://ms-catalog"))
						.route(r -> r.path("/v1/installments/**").uri("lb://ms-payment"))
						.route(r -> r.path("/v1/payments/**").uri("lb://ms-payment"))
						.route(r -> r.path("/v1/orders/**").uri("lb://ms-order"))
					.build();
	}
}
