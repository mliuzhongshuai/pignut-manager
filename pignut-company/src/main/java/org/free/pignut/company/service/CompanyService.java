package org.free.pignut.company.service;

import org.free.pignut.common.enums.CompanyBusEnum;
import org.free.pignut.common.vo.BusResult;
import org.free.pignut.common.vo.company.CompanyVo;
import org.free.pignut.company.dao.CompanyDao;
import org.free.pignut.company.dto.Company;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liuzhongshuai on 2017/9/21.
 */
@Service
@Transactional
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;


    /**
     * 通过主键查询公司信息
     *
     * @param id
     * @return
     */
    public BusResult<CompanyVo> getCompany(Long id) {
        BusResult<CompanyVo> busResult = new BusResult<CompanyVo>();
        //查询未删除状态
        Company company = companyDao.findByIdAndStatus(id, 1);

        if (null == company) {
            busResult.setReturnCode(CompanyBusEnum.SEARCH_COMPANY_ID_NULL.getCode());
            return busResult;
        }

        CompanyVo companyVo = new CompanyVo();
        BeanUtils.copyProperties(company, companyVo);

        busResult.setReturnCode(CompanyBusEnum.SUCCESS.getCode());
        busResult.setData(companyVo);
        return busResult;
    }

    /**
     * 通过所有者查询公司信息
     *
     * @param ownerId
     * @return
     */
    public BusResult<List<CompanyVo>> findCompany(Long ownerId) {
        BusResult<List<CompanyVo>> busResult = new BusResult<List<CompanyVo>>();
        //查询未删除状态
        List<Company> companys = companyDao.findByOwnerId(ownerId);

        if (CollectionUtils.isEmpty(companys)) {
            busResult.setReturnCode(CompanyBusEnum.SEARCH_COMPANY_ID_NULL.getCode());
            return busResult;
        }
        List<CompanyVo> voList = new ArrayList<CompanyVo>();

        for (Company company : companys) {
            CompanyVo companyVo = new CompanyVo();
            BeanUtils.copyProperties(company, companyVo);
            voList.add(companyVo);
        }
        busResult.setReturnCode(CompanyBusEnum.SUCCESS.getCode());
        busResult.setData(voList);
        return busResult;
    }

    /**
     * 修改公司信息
     *
     * @param companyVo
     * @return
     */
    public BusResult<CompanyVo> modifyCompany(CompanyVo companyVo) {

        BusResult<CompanyVo> busResult = new BusResult<CompanyVo>();

        Company company = companyDao.findOne(companyVo.getId());

        if (null == company) {
            busResult.setReturnCode(CompanyBusEnum.SEARCH_COMPANY_ID_NULL.getCode());
            return busResult;
        }
        BeanUtils.copyProperties(companyVo, company);
        company.setModifyTime(new Date());

        companyDao.save(company);

        busResult.setReturnCode(CompanyBusEnum.SUCCESS.getCode());
        return busResult;
    }

    /**
     * 保存公司信息
     *
     * @param companyVo
     * @return
     */
    public BusResult<CompanyVo> saveCompany(CompanyVo companyVo) {

        BusResult<CompanyVo> busResult = new BusResult<CompanyVo>();

        Company company = new Company();
        BeanUtils.copyProperties(companyVo, company);
        company.setModifyTime(new Date());
        company.setCreateTime(new Date());

        companyDao.save(company);

        BeanUtils.copyProperties(company, companyVo);

        busResult.setReturnCode(CompanyBusEnum.SUCCESS.getCode());
        busResult.setData(companyVo);
        return busResult;

    }

    /**
     * 删除公司信息
     *
     * @param id：主键
     * @param ownerId ：公司所有者Id
     * @return
     */
    public BusResult<CompanyVo> delCompany(Long id, Long ownerId) {

        BusResult<CompanyVo> busResult = new BusResult<CompanyVo>();

        Company company = companyDao.findByIdAndOwnerId(id, ownerId);

        if (null == company) {
            busResult.setReturnCode(CompanyBusEnum.SEARCH_COMPANY_ID_NULL.getCode());
            return busResult;
        }
        company.setStatus(1);
        company.setModifyTime(new Date());
        companyDao.save(company);

        busResult.setReturnCode(CompanyBusEnum.SUCCESS.getCode());
        return busResult;


    }


}
