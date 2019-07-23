package com.stylefeng.guns.rest.modular.user.vo;

import com.stylefeng.guns.rest.modular.user.validator.dto.Credence;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserAuthRequest implements Credence, Serializable {

    private static final long serialVersionUID = -1403342312682355781L;
    private String userName;
    private String password;

    @Override
    public String getCredenceName() {
        return this.userName;
    }

    @Override
    public String getCredenceCode() {
        return this.password;
    }
}
