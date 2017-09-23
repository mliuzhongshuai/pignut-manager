package org.free.pignut.company.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.free.pignut.common.enums.CompanyBusEnum;
import org.free.pignut.common.enums.UserBusEnum;
import org.free.pignut.common.vo.BaseBody;
import org.free.pignut.common.vo.BusResult;
import org.free.pignut.common.vo.company.CompanyVo;
import org.free.pignut.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLDataException;
import java.util.List;

/**
 * Created by liuzhongshuai on 2017/9/12.
 */
@Api("店铺/公司管理")
@RestController
@RequestMapping("/company")
public class CompanyController {


    @Autowired
    private CompanyService companyService;


    @ApiOperation("通过id查询公司信息")
    @GetMapping("/{id}")
    public BaseBody<CompanyVo> getCompany(@RequestParam(defaultValue = "0", required = true) @PathVariable(name = "id") Long id) {

        BaseBody<CompanyVo> baseBody = new BaseBody<CompanyVo>();

        if (id == null || id == 0) {
            baseBody.setReturnMsg("参数有误");
            baseBody.setReturnCode(CompanyBusEnum.PARAM_ERROR.getCode());
            baseBody.setReturnResult("FAILD");
            return baseBody;
        }
        BusResult<CompanyVo> busResult = companyService.getCompany(id);
        //查询失败!
        if (!busResult.getReturnCode().equals(CompanyBusEnum.SUCCESS.getCode())) {
            baseBody.setReturnResult("FAILD");
            baseBody.setReturnCode(busResult.getReturnCode());
            baseBody.setReturnMsg(CompanyBusEnum.findByCode(busResult.getReturnCode()).getName());
            return baseBody;
        }

        baseBody.setData(busResult.getData());
        baseBody.setReturnCode(CompanyBusEnum.SUCCESS.getCode());
        baseBody.setReturnMsg("查询公司信息成功!");
        return baseBody;
    }


    @ApiOperation("查询用户下的公司列表")
    @GetMapping("/{ownerId}")
    public BaseBody<List<CompanyVo>> findCompany(@RequestParam(defaultValue = "0", required = true) @PathVariable(name = "ownerId") Long ownerId) {

        BaseBody<List<CompanyVo>> baseBody = new BaseBody<List<CompanyVo>>();

        if (ownerId == 0) {
            baseBody.setReturnMsg("参数有误");
            baseBody.setReturnCode(CompanyBusEnum.PARAM_ERROR.getCode());
            baseBody.setReturnResult("FAILD");
            return baseBody;
        }
        BusResult<List<CompanyVo>> busResult = companyService.findCompany(ownerId);
        //查询失败!
        if (!busResult.getReturnCode().equals(CompanyBusEnum.SUCCESS.getCode())) {
            baseBody.setReturnResult("FAILD");
            baseBody.setReturnCode(busResult.getReturnCode());
            baseBody.setReturnMsg(CompanyBusEnum.findByCode(busResult.getReturnCode()).getName());
            return baseBody;
        }

        baseBody.setData(busResult.getData());
        baseBody.setReturnCode(CompanyBusEnum.SUCCESS.getCode());
        baseBody.setReturnMsg("查询公司信息成功!");
        return baseBody;
    }

    @ApiOperation("修改/新增公司信息")
    @PostMapping("/modifyOrSaveCompany")
    public BaseBody<CompanyVo> modifyCompany(@RequestBody @Valid CompanyVo companyVo,BindingResult bindingResult) throws SQLDataException {
        BaseBody<CompanyVo> baseBody = new BaseBody<CompanyVo>();

        if (bindingResult.hasErrors()) {
            baseBody.setReturnResult("FAILD");
            baseBody.setReturnCode(UserBusEnum.PARAM_ERROR.getCode());
            baseBody.setReturnMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return baseBody;
        }

        BusResult<CompanyVo> busResult = null;
        //新增
        if (null == companyVo.getId() || 0 == companyVo.getId()) {
            busResult = companyService.saveCompany(companyVo);
        } else {//修改
            busResult = companyService.modifyCompany(companyVo);
        }
        if (!busResult.getReturnCode().equals(CompanyBusEnum.SUCCESS.getCode())) {
            baseBody.setReturnResult("FAILD");
            baseBody.setReturnCode(busResult.getReturnCode());
            baseBody.setReturnMsg(CompanyBusEnum.findByCode(busResult.getReturnCode()).getName());
            return baseBody;
        }

        baseBody.setData(busResult.getData());
        baseBody.setReturnCode(CompanyBusEnum.SUCCESS.getCode());
        baseBody.setReturnMsg("操作成功!");
        return baseBody;
    }

    @ApiOperation("删除公司信息")
    @DeleteMapping("/{id}/{ownerId}")
    public BaseBody<CompanyVo> delCompany(@RequestParam(defaultValue = "0") @PathVariable Long id, @RequestParam(defaultValue = "0") @PathVariable Long ownerId) {
        BaseBody<CompanyVo> baseBody = new BaseBody<CompanyVo>();
        if (id == 0 || ownerId == 0) {
            baseBody.setReturnMsg("参数有误");
            baseBody.setReturnCode(CompanyBusEnum.PARAM_ERROR.getCode());
            baseBody.setReturnResult("FAILD");
            return baseBody;
        }

        BusResult<CompanyVo> busResult = companyService.delCompany(id, ownerId);
        if (!busResult.getReturnCode().equals(CompanyBusEnum.SUCCESS.getCode())) {
            baseBody.setReturnResult("FAILD");
            baseBody.setReturnCode(busResult.getReturnCode());
            baseBody.setReturnMsg(CompanyBusEnum.findByCode(busResult.getReturnCode()).getName());
            return baseBody;
        }
        baseBody.setData(busResult.getData());
        baseBody.setReturnCode(CompanyBusEnum.SUCCESS.getCode());
        baseBody.setReturnMsg("删除成功!");
        return baseBody;
    }


}
