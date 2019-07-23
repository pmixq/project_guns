package com.stylefeng.guns.rest.modular.user.vo;


import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
public class UserInfoData implements Serializable {
    private static final long serialVersionUID = -5465688666078598056L;
    int uuid;
    String username;
    String nickname;
    String email;
    String phone;
    int sex;
    String birthday;
    String lifeState;
    String  biography;
    String address;
    String headAddress;
    long createTime;
    long updateTime;


}
