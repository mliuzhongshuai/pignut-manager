package org.free.pignut.user.service;

import org.free.pignut.common.exception.KeyNullException;
import org.free.pignut.common.util.AESUtil;
import org.free.pignut.common.enums.UserBusEnum;
import org.free.pignut.common.vo.BusResult;
import org.free.pignut.common.vo.user.UserVo;
import org.free.pignut.user.dao.UserDao;
import org.free.pignut.user.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by liuzhongshuai on 2017/9/15.
 */
@Service
@Transactional
public class UserService {


    private final static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDao userDao;

    @Value("${pignut.user.register.key}")
    private String key;

    /**
     * 用户注册
     *
     * @param userVo
     * @return
     * @throws UnsupportedEncodingException
     */
    public BusResult<UserVo> userRegister(UserVo userVo) throws UnsupportedEncodingException {
        BusResult busResult = new BusResult();

        User user = new User();
        BeanUtils.copyProperties(userVo, user);

        //用户名唯一验证
        User userRes = userDao.findByUserName(userVo.getUserName());
        if (userRes != null && user.getId() > 0) {
            busResult.setReturnCode(UserBusEnum.VALID_USER_USERNAME_UNINE_FALSE.getCode());
            return busResult;
        }
        //密码加盐（用户名+密码+秘钥 aes）
        if(StringUtils.isEmpty(key)){
            throw new KeyNullException(UserBusEnum.USER_KEY_NULL.getCode(),"秘钥为null");
        }

        byte[] bytes = AESUtil.encrypt((userVo.getUserName() + userVo.getPassWord()), key);
        user.setPassword(AESUtil.parseByte2HexStr(bytes));

        user.setCreateTime(new Date());
        user.setModifyTime(new Date());

        user = userDao.save(user);
        userVo.setId(user.getId());

        busResult.setData(userVo);
        busResult.setReturnCode(UserBusEnum.SUCCESS.getCode());

        return busResult;
    }


    /**
     * 用户登录
     *
     * @param userName
     * @param password
     * @return
     * @throws UnsupportedEncodingException
     */
    public BusResult<UserVo> userLogin(String userName, String password) throws UnsupportedEncodingException {

        BusResult<UserVo> busResult = new BusResult<UserVo>();
        User user = userDao.findByUserName(userName);
        //用户未注册
        if (user == null) {
            busResult.setReturnCode(UserBusEnum.SEARCH_USER_USERNAME_NULL.getCode());
            return busResult;
        }
        //用户状态已被禁用
        if ("1".equals(user.getStatus())) {
            busResult.setReturnCode(UserBusEnum.VALID_USER_STATUS_DELETED.getCode());
            return busResult;
        }

        byte[] bytes1 = AESUtil.decrypt(AESUtil.parseHexStr2Byte(user.getPassword()), key);
        String hexStr = new String(bytes1, "UTF-8");
        //密码验证未通过
        if (!(userName + password).equals(hexStr)) {
            busResult.setReturnCode(UserBusEnum.SEARCH_USER_ID_NULL.getCode());
            return busResult;
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        userVo.setPassWord(null);

        busResult.setReturnCode(UserBusEnum.SUCCESS.getCode());
        busResult.setData(userVo);

        return busResult;
    }


    /**
     * 通过Id查询用户
     *
     * @param id
     * @return
     */
    public BusResult<UserVo> getUser(Long id) {
        BusResult<UserVo> busResult = new BusResult<UserVo>();

        User user = userDao.findById(id);
        if (null == user) {
            busResult.setReturnCode(UserBusEnum.SEARCH_USER_ID_NULL.getCode());
            return busResult;
        }

        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        busResult.setReturnCode(UserBusEnum.SUCCESS.getCode());
        busResult.setData(userVo);
        return busResult;
    }


    /**
     * 禁用、删除用户
     * @param id
     * @return
     */
    public BusResult<UserVo> delUser(Long id){
        BusResult<UserVo> busResult = new BusResult<UserVo>();

        User user = userDao.findById(id);
        if (null == user) {
            busResult.setReturnCode(UserBusEnum.SEARCH_USER_ID_NULL.getCode());
            return busResult;
        }
        //用户已经是 禁用状态
        if("1".equals(user.getStatus())){
            busResult.setReturnCode(UserBusEnum.VALID_USER_STATUS_DELETED.getCode());
            return busResult;
        }
        int effRow=userDao.updateUserByStatus("1", id);
        busResult.setReturnCode(UserBusEnum.SUCCESS.getCode());
        return busResult;
    }

}
