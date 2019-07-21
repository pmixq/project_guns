package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author WPM
 * @Create 2019/7/20 17:29
 **/
@Data
public class FilmInfoVO implements Serializable {

    private static final long serialVersionUID = -8304461028370012884L;

    private String filmId;
    private Integer filmType;
    private String imgAddress;
    private String filmName;
    private String filmScore;

    private Date showTime;
    private Integer expectNum;
}
