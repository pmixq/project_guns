package com.stylefeng.guns.rest.modular.user.service.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.rest.common.persistence.dao.MtimeUserTMapper;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;
import com.stylefeng.guns.rest.modular.user.MtimeUserT;
import com.stylefeng.guns.rest.modular.user.UserAuthRequest;
import com.stylefeng.guns.rest.modular.user.service.UserService;
import com.stylefeng.guns.rest.modular.user.util.Md5Util;
import com.stylefeng.guns.rest.modular.user.vo.RegisterIn;
import com.stylefeng.guns.rest.modular.user.vo.UserInfoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
@Component
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {
    @Autowired
    MtimeUserTMapper userMapper;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Resource(name = "simpleValidator")
    IReqValidator reqValidator;


    @Override
    public Boolean insertUser(RegisterIn registerIn) {
        String password = registerIn.getPassword();
        //密码加密
        String passwordDB = Md5Util.getMD5(password);
        registerIn.setPassword(passwordDB);

        int i = userMapper.insertRegisterIn(registerIn);
        if (i == 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean checkUsername(String username) {
        int s = userMapper.countByUsername(username);
        if (s != 0 ){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Boolean usernameOrPasswordNull(String username, String password) {
        if (username == null || "".equals(username) || password == null || "".equals(password)){
            return true;
        }
        return false;
    }

    @Override
    public Boolean validate(UserAuthRequest authRequest) {
        boolean validate = reqValidator.validate(authRequest);
        return validate;
    }

    @Override
    public Boolean LogOut() {
        return null;
    }

    @Override
    public UserInfoData getUserInfoByName(String username) {
        UserInfoData userInfoData = userMapper.selectUserInfoByName(username);
        return userInfoData;
    }

    @Override
    public UserInfoData getUserInfoById(Integer uuid) {
        UserInfoData userInfoData = userMapper.selectUserInfoById(uuid);
        return userInfoData;
    }

    @Override
    public Boolean updateUserInfo(UserInfoData userInfoData) {
        int i = userMapper.updateUserInfo(userInfoData);
        if (i ==1){
            return true;
        }
        return false;
    }

    @Override
    public String getPasswordByUsername(String username) {
        String passwordByUsername = userMapper.getPasswordByUsername(username);
        return passwordByUsername;
    }
    @Override
    public String jedisStoreToken(String userTokenKey, String token) {
        Jedis jedis = new Jedis();
        String result = jedis.set(userTokenKey, token);
        jedis.close();
        return result;
    }

    @Override
    public String jedisStoreUserMsg(String username) {
        //1.查出该用户的信息
        MtimeUserT mtimeUserT = userMapper.selectByUsername(username);
        //2.将用户信息转换成json格式数据(使用fastjson)
        String jsonOfUsermsg = JSON.toJSONString(mtimeUserT);
        System.out.println("jsonOfUsermsg = " + jsonOfUsermsg);

        //3.存入redis里，key=username,value=json字符串
        Jedis jedis = new Jedis();
        String result = jedis.set(username, jsonOfUsermsg);
        jedis.close();
        return result;
    }

    @Override
    public Boolean jedisTokenExist(String userTokenKey, String authToken) {
        Jedis jedis = new Jedis();
        String tokenInRedis = jedis.get(userTokenKey);
        if (authToken.equals(tokenInRedis)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean jedisTokenClean(String userTokenKey, String authToken) {
        Jedis jedis = new Jedis();
        String result = jedis.setex(userTokenKey, 1, authToken);
        if ("OK".equalsIgnoreCase(result)){
            return true;
        }
        return false;
    }


    public int getUseridByName(String username) {
        Integer uuid =  userMapper.getUserIdByName(username);
        return uuid;
    }
}
