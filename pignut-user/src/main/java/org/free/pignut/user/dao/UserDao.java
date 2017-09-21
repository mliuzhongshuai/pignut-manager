package org.free.pignut.user.dao;

import org.free.pignut.user.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by liuzhongshuai on 2017/9/15.
 */
public interface UserDao extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findById(Long id);

    @Modifying
    @Query(value = "update tb_user t set t.status=?1 where t.id=?2",nativeQuery = true)
    int updateUserByStatus(String status,Long id);

}
