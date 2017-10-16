package org.free.pignut.product;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author liuzhongshuai
 * Created by liuzhongshuai on 2017/9/29.
 */
@SpringCloudApplication
@EnableHystrixDashboard
@EnableFeignClients(basePackages = {"org.free.pignut.common.client"})
@ComponentScan(basePackages = {"springfox", "org.free.pignut"})
public class ProductServerStarter {

    public static void main(String[] args) {

        new SpringApplicationBuilder(ProductServerStarter.class).web(true).run(args);
    }
}
