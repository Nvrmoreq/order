package com.springcloud.eurekaclient01;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/*@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker*/
@SpringCloudApplication
@EnableFeignClients(basePackages = "com.springcloud.client")
@ComponentScan(basePackages = "com.springcloud")
@EnableHystrixDashboard
public class EurekaClient01Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClient01Application.class, args);
    }


}
