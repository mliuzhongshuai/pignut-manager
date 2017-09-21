package org.free.pignut.user;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by liuzhongshuai on 2017/8/30.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
//只扫描本类所在包，要扫描其他包，必须配置
@ComponentScan(basePackages = {"springfox","org.free.pignut"})
public class UserServerStarter {

    public static void main(String[] args){
        new SpringApplicationBuilder(UserServerStarter.class).web(true).run(args);
    }


}
