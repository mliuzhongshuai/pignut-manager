package org.free.pignut.zuul;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by liuzhongshuai on 2017/9/2.
 */
@EnableZuulProxy
@SpringCloudApplication
public class PignutZuulStarter {

    public static void main(String[] args){
        new SpringApplicationBuilder(PignutZuulStarter.class).web(true).run(args);
    }


}
