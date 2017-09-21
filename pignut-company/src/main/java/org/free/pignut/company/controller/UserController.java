package org.free.pignut.company.controller;


import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuzhongshuai on 2017/9/2.
 */
@RestController
public class UserController {
/*

    @Autowired
    private UserControllerClient userControllerClient;

    @Autowired
    private User2ControllerClient user2ControllerClient;

    @RequestMapping(value = "/controller/100001", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "helloFallback"*/
/*,threadPoolProperties = {@HystrixProperty(name = "coreSize",value="20")}*//*
)
    String findUserById() {
        return userControllerClient.hellow();
    }

   */
/* @RequestMapping(value = "/controller/20000", method = RequestMethod.GET)
    String say(){
        return user2ControllerClient.say();
    }*//*


    @RequestMapping(value = "/controller/name", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "helloFallback",threadPoolProperties = {@HystrixProperty(name = "coreSize",value="10")})
    String findUserByName(){

        return "刘中帅";
    }


    public String helloFallback() {

        return "error";
    }
*/


}
