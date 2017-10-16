package org.free.pignut.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.free.pignut.common.enums.UserBusEnum;
import org.free.pignut.common.vo.BaseBody;
import org.free.pignut.common.vo.BusResult;
import org.free.pignut.common.vo.user.UserVo;
import org.free.pignut.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

/**
 * Created by liuzhongshuai on 2017/8/30.
 */
@Api("用户操作")
@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /**
     * 用户注册,用户注册
     *
     * @param userVo
     * @return
     */
    @ApiOperation("用户注册")
    @PostMapping("/register")
    public BaseBody<UserVo> userRegister(@RequestBody @Valid @ApiParam(name = "userVo") UserVo userVo, BindingResult bindingResult) {

        BaseBody<UserVo> baseBody = new BaseBody<UserVo>();
        if (bindingResult.hasErrors()) {
            baseBody.setReturnResult("FAILD");
            baseBody.setReturnCode(UserBusEnum.PARAM_ERROR.getCode());
            baseBody.setReturnMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return baseBody;
        }
        //注册用户
        BusResult<UserVo> busResult = null;
        try {
            busResult = userService.userRegister(userVo);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("用户注册加密异常:{}", e.getMessage());
            baseBody.setReturnResult("FAILD");
            baseBody.setReturnCode(4444);
            baseBody.setReturnMsg("程序异常,请联系管理员!");
            return baseBody;
        }

        if (!busResult.getReturnCode().equals(UserBusEnum.SUCCESS.getCode())) {
            baseBody.setReturnResult("FAILD");
            baseBody.setReturnCode(busResult.getReturnCode());
            baseBody.setReturnMsg(UserBusEnum.findByCode(busResult.getReturnCode()).getName());
            return baseBody;
        }

        baseBody.setReturnResult("SUCCESS");
        baseBody.setReturnCode(UserBusEnum.SUCCESS.getCode());
        baseBody.setReturnMsg("用户注册成功!");
        baseBody.setData(userVo);
        return baseBody;
    }

    /**
     * 用户登录
     *
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public BaseBody<UserVo> userLogin(@RequestBody UserVo userVo) throws UnsupportedEncodingException {

        BaseBody<UserVo> baseBody = new BaseBody<UserVo>();
        String userName = userVo.getUserName();
        String password = userVo.getPassWord();
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            baseBody.setReturnCode(UserBusEnum.PARAM_ERROR.getCode());
            baseBody.setReturnMsg("用户名或密码为空!");
            return baseBody;
        }
        BusResult<UserVo> busResult = userService.userLogin(userName, password);
        //登录失败
        if (!busResult.getReturnCode().equals(UserBusEnum.SUCCESS.getCode())) {
            baseBody.setReturnResult("FAILD");
            baseBody.setReturnCode(busResult.getReturnCode());
            baseBody.setReturnMsg(UserBusEnum.findByCode(busResult.getReturnCode()).getName());
            return baseBody;
        }

        baseBody.setReturnCode(UserBusEnum.SUCCESS.getCode());
        baseBody.setData(busResult.getData());
        baseBody.setReturnMsg("登录成功!");
        return baseBody;
    }


    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    @ApiOperation("通过id获取用户")
    @GetMapping("/{id}")
    public BaseBody<UserVo> getUser(@PathVariable Long id) {
        BaseBody<UserVo> baseBody = new BaseBody<UserVo>();

        if (id == null || id == 0) {
            baseBody.setReturnMsg("参数有误");
            baseBody.setReturnCode(UserBusEnum.PARAM_ERROR.getCode());
            baseBody.setReturnResult("FAILD");
            return baseBody;
        }
        BusResult<UserVo> busResult = userService.getUser(id);
        //查询失败!
        if (!busResult.getReturnCode().equals(UserBusEnum.SUCCESS.getCode())) {
            baseBody.setReturnResult("FAILD");
            baseBody.setReturnCode(busResult.getReturnCode());
            baseBody.setReturnMsg(UserBusEnum.findByCode(busResult.getReturnCode()).getName());
            return baseBody;
        }

        baseBody.setData(busResult.getData());
        baseBody.setReturnCode(UserBusEnum.SUCCESS.getCode());
        baseBody.setReturnMsg("查询用户成功!");

        return baseBody;
    }

    @ApiOperation("删除、禁用用户")
    @DeleteMapping("/{id}")
    public BaseBody<UserVo> delUser(@PathVariable Long id) {
        BaseBody<UserVo> baseBody = new BaseBody<UserVo>();
        if (id == null || id == 0) {
            baseBody.setReturnMsg("参数有误");
            baseBody.setReturnCode(UserBusEnum.PARAM_ERROR.getCode());
            baseBody.setReturnResult("FAILD");
            return baseBody;
        }
        BusResult<UserVo> busResult = userService.delUser(id);

        if (!busResult.getReturnCode().equals(UserBusEnum.SUCCESS.getCode())) {
            baseBody.setReturnResult("FAILD");
            baseBody.setReturnCode(busResult.getReturnCode());
            baseBody.setReturnMsg(UserBusEnum.findByCode(busResult.getReturnCode()).getName());
            return baseBody;
        }
        baseBody.setReturnCode(UserBusEnum.SUCCESS.getCode());
        baseBody.setReturnMsg("删除用户成功!");
        return baseBody;
    }

}
