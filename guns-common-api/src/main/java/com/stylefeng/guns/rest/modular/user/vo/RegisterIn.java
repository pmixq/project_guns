package com.stylefeng.guns.rest.modular.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
public class RegisterIn implements Serializable {

    private static final long serialVersionUID = -4261903160726052304L;
    String username;
    String password;
    String email;
    String phone;
    String address;


}
