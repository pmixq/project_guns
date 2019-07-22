package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Parameter implements Serializable {

    private static final long serialVersionUID = -4798709370937989990L;

    private int showType;
    private int sortId;
    private int catId;
    private int sourceId;
    private int yearId;
    private int nowPage;
    private int pageSize;
    private String kw;
}
