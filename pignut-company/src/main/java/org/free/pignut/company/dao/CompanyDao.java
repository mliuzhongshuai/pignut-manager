package org.free.pignut.company.dao;

import org.free.pignut.company.dto.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by liuzhongshuai on 2017/9/21.
 */
public interface CompanyDao extends JpaRepository<Company, Long> {


    Company findByIdAndStatus(Long id,Integer status);

    List<Company> findByOwnerId(Long ownerId);

    Company findByIdAndOwnerId(Long id,Long ownerId);


}
