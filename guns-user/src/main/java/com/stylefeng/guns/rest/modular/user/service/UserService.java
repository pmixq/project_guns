package com.stylefeng.guns.rest.modular.user.service;

import com.stylefeng.guns.rest.modular.user.vo.RegisterIn;
import com.stylefeng.guns.rest.modular.user.vo.UserInfoData;
import com.stylefeng.guns.rest.modular.user.UserAuthRequest;


/**
 * @author Administrator
 */
public interface UserService {
    Boolean insertUser(RegisterIn registerIn);

    Boolean checkUsername(String username);

    Boolean usernameOrPasswordNull(String var1, String var2);

    Boolean validate(UserAuthRequest authRequest);

    Boolean LogOut();

    UserInfoData getUserInfoByName(String username);

    UserInfoData getUserInfoById(Integer uuid);

    Boolean updateUserInfo(UserInfoData userInfoData);

    String getPasswordByUsername(String  username);

    String jedisStoreToken(String userTokenKey, String token);

    String jedisStoreUserMsg(String username);

    Boolean jedisTokenExist(String userTokenKey, String authToken);

    public Boolean jedisTokenClean(String userTokenKey, String authToken);

    int getUseridByName(String username);



}
