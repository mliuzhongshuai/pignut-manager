package org.free.pignut.company;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by liuzhongshuai on 2017/9/2.
 */

@SpringCloudApplication
@EnableHystrixDashboard
@EnableFeignClients(basePackages = {"org.free.pignut.common.client"})
@ComponentScan(basePackages = {"springfox","org.free.pignut.company"})
public class CompanyServerStarter {

    public static void main(String[] args) {

        new SpringApplicationBuilder(CompanyServerStarter.class).web(true).run(args);
    }
}
