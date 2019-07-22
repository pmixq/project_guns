package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WPM
 * @Create 2019/7/19 19:48
 **/
@Data
public class OrderVO implements Serializable {

    private static final long serialVersionUID = 9017014199470981837L;

    private String orderId;

    private String filmName;

    private String fieldTime;

    private String cinemaName;

    private String seatsName;

    private double orderPrice;

    private String orderTimestamp;

    private String orderStatus;
}
