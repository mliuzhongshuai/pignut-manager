package org.free.pignut.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by liuzhongshuai on 2017/8/30.
 * @author 刘中帅
 */
@EnableEurekaServer
@EnableDiscoveryClient
@SpringBootApplication
public class PignutEurekaStarter {

    public static void main(String[] args) {
        new SpringApplicationBuilder(PignutEurekaStarter.class).web(true).run(args);
    }
}
