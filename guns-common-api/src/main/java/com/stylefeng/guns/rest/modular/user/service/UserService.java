package com.stylefeng.guns.rest.modular.user.service;

import com.stylefeng.guns.rest.modular.user.validator.dto.Credence;
import com.stylefeng.guns.rest.modular.user.vo.UserAuthRequest;
import com.stylefeng.guns.rest.modular.user.vo.RegisterIn;
import com.stylefeng.guns.rest.modular.user.vo.UserInfoData;


/**
 * @author Administrator
 */
public interface UserService {
    Boolean insertUser(RegisterIn registerIn);

    Boolean checkUsername(String username);

    Boolean usernameOrPasswordNull(String var1, String var2);



    Boolean LogOut();

    UserInfoData getUserInfoByName(String username);

    UserInfoData getUserInfoById(Integer uuid);

    Boolean updateUserInfo(UserInfoData userInfoData);

    String getPasswordByUsername(String username);

    String jedisStoreToken(String userTokenKey, String token);

    String jedisStoreUserMsg(String username);

    Boolean jedisTokenExist(String userTokenKey, String authToken);

    public Boolean jedisTokenClean(String userTokenKey, String authToken);

    int getUseridByName(String username);


    boolean validate(Credence credence);
}
