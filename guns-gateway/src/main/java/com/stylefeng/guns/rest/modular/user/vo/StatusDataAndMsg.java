package com.stylefeng.guns.rest.modular.user.vo;

import lombok.Data;

@Data
public class StatusDataAndMsg<T> {
    int status;
    String msg;
    T data;
}
