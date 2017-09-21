package org.free.pignut.company.controller;

import io.swagger.annotations.ApiOperation;
import org.free.pignut.common.enums.UserBusEnum;
import org.free.pignut.common.vo.BaseBody;
import org.free.pignut.common.vo.BusResult;
import org.free.pignut.common.vo.company.CompanyVo;
import org.free.pignut.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuzhongshuai on 2017/9/12.
 */
@RestController
@RequestMapping("/company")
public class CompanyController {


        @Autowired
        private CompanyService companyService;


        @ApiOperation("通过id查询公司信息")
        @RequestMapping("/{id}")
        public BaseBody<CompanyVo> getCompany(@PathVariable(name="id") Long id){

            BaseBody<CompanyVo> baseBody = new BaseBody<CompanyVo>();

            if (id == null || id == 0) {
                baseBody.setReturnMsg("参数有误");
                baseBody.setReturnCode(UserBusEnum.PARAM_ERROR.getCode());
                baseBody.setReturnResult("FAILD");
                return baseBody;
            }
            BusResult<CompanyVo> busResult = companyService.getCompany(id);
            //查询失败!
            if (busResult.getReturnCode() != UserBusEnum.SUCCESS.getCode()) {
                baseBody.setReturnResult("FAILD");
                baseBody.setReturnCode(busResult.getReturnCode());
                baseBody.setReturnMsg(UserBusEnum.findByCode(busResult.getReturnCode()).getName());
                return baseBody;
            }

            baseBody.setData(busResult.getData());
            baseBody.setReturnCode(UserBusEnum.SUCCESS.getCode());
            baseBody.setReturnMsg("查询公司信息成功!");
            return baseBody;

        }





}
