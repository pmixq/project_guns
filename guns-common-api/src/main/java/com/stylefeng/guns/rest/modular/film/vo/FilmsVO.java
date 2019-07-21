package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author WPM
 * @Create 2019/7/20 17:26
 **/
@Data
public class FilmsVO implements Serializable {
    private static final long serialVersionUID = 6720869842697536645L;

    private Integer filmNum;

    private List<FilmInfoVO> filmInfo;
}
