package org.free.pignut.user.dao;

import org.free.pignut.user.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author liuzhongshuai
 * Created by liuzhongshuai on 2017/9/15.
 */
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * 通过用户名查询
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 通过用户状态更新用户
     * @param status
     * @param id
     * @return
     */
    @Modifying
    @Query(value = "update tb_user t set t.status=?1 where t.id=?2",nativeQuery = true)
    int updateUserByStatus(String status,Long id);

}
