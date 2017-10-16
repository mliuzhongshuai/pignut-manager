package org.free.pignut.product.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.free.pignut.common.enums.CompanyBusEnum;
import org.free.pignut.common.vo.BaseBody;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuzhongshuai
 * Created by liuzhongshuai on 2017/9/29.
 */
@Api("产品信息")
@RestController
@RequestMapping("/product")
public class ProductController {


    @ApiOperation("通过id查询产品信息")
    @GetMapping("/{id}")
    public BaseBody<Object> getProduct(@RequestParam(defaultValue = "0", required = true) @PathVariable(name = "id") Long id) {

        BaseBody<Object> baseBody = new BaseBody<Object>();

        baseBody.setReturnCode(CompanyBusEnum.SUCCESS.getCode());
        baseBody.setReturnMsg("查询产品信息成功!");
        return baseBody;
    }
}
