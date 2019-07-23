package com.stylefeng.guns.rest.modular.auth.validator.impl;

import com.stylefeng.guns.rest.common.persistence.dao.MtimeUserTMapper;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;
import com.stylefeng.guns.rest.modular.user.util.Md5Util;

import com.stylefeng.guns.rest.modular.user.validator.dto.Credence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 直接验证账号密码是不是admin
 *
 * @author fengshuonan
 * @date 2017-08-23 12:34
 */
@Service
public class SimpleValidator implements IReqValidator {
/*

    private static String USER_NAME = "admin";

    private static String PASSWORD = "admin";

    @Override
    public boolean validate(Credence credence) {

        String userName = credence.getCredenceName();
        String password = credence.getCredenceCode();

        if (USER_NAME.equals(userName) && PASSWORD.equals(password)) {
            return true;
        } else {
            return false;
        }
    }
*/

    @Autowired
    MtimeUserTMapper userMapper;

    @Override
    public boolean validate(Credence credence) {
        String userName = credence.getCredenceName();
        String password = credence.getCredenceCode();

        //将传入的密码加密后与从数据库取出username对应的password对照

        String passwordInDB = userMapper.getPasswordByUsername(userName);

        if (passwordInDB.equals(Md5Util.getMD5(password))){
            //密码正确
            return true;
        }else {
            return false;
        }
    }


}
