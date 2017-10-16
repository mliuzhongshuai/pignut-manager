package org.free.pignut.company.dao;

import org.free.pignut.company.dto.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author liuzhongshuai
 * Created by liuzhongshuai on 2017/9/21.
 */
public interface CompanyDao extends JpaRepository<Company, Long> {


    /**
     * 通过 id 和数据状态查询公司信息
     * @param id
     * @param status
     * @return
     */
    Company findByIdAndStatus(Long id,Integer status);

    /**
     * 通过所属者查询公司信息
     * @param ownerId
     * @return
     */
    List<Company> findByOwnerId(Long ownerId);

    /**
     * 通过 id 和所属者 查询公司信息
     * @param id
     * @param ownerId
     * @return
     */
    Company findByIdAndOwnerId(Long id,Long ownerId);


}
