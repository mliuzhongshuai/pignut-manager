package org.free.pignut.common.client.user;

import org.free.pignut.common.vo.BaseBody;
import org.free.pignut.common.vo.user.UserVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author liuzhongshuai
 * Created by liuzhongshuai on 2017/9/15.
 */
@RestController
@RequestMapping("/user")
@FeignClient("pignut-user")
public interface UserClientApi {


    /**
     * 用户注册
     *
     * @param userVo
     * @param bindingResult
     * @return
     */
    @PostMapping("/register")
    BaseBody<UserVo> userRegister(@RequestBody @Valid UserVo userVo, BindingResult bindingResult);


    /**
     * 用户登录
     * @param userVo
     * @param bindingResult
     * @return
     */
    @PostMapping("/login")
    BaseBody<UserVo> userLogin(@RequestBody @Valid UserVo userVo, BindingResult bindingResult);


    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    BaseBody<UserVo> getUser(@PathVariable Long id);


}
