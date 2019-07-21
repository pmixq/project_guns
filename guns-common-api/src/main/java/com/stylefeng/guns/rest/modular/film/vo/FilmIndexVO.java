package com.stylefeng.guns.rest.modular.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author WPM
 * @Create 2019/7/21 13:40
 **/
@Data
public class FilmIndexVO implements Serializable {
    private static final long serialVersionUID = 4803462428288523321L;

    private List<BannerVO> banners;
    private List<FilmRankVO> expectRanking;
    private FilmsVO hotFilms;
    private List<FilmRankVO> boxRanking;
    private FilmsVO soonFilms;
    private List<FilmRankVO> top100;

}
