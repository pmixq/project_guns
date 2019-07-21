package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BannerVO implements Serializable {

    private static final long serialVersionUID = 5608316357047694081L;

    private String bannerId;
    private String bannerAddress;
    private String bannerUrl;

}
