package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author WPM
 * @Create 2019/7/20 17:34
 **/
@Data
public class FilmRankVO implements Serializable {
    private static final long serialVersionUID = 6019875013245690801L;

    private String filmId;
    private String imgAddress;
    private String filmName;

    private Integer boxNum;
    private Integer expectNum;
    private String score;
}
