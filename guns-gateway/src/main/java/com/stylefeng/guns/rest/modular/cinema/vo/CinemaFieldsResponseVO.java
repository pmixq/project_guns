package com.stylefeng.guns.rest.modular.cinema.vo;

import com.stylefeng.guns.rest.modular.vo.CinemaInfoVO;

import java.util.List;

public class CinemaFieldsResponseVO {
    private CinemaInfoVO cinemaInfo;
    private List<FilmInfoVO> filmList;

    public CinemaInfoVO getCinemaInfo() {
        return cinemaInfo;
    }

    public void setCinemaInfo(CinemaInfoVO cinemaInfo) {
        this.cinemaInfo = cinemaInfo;
    }

    public List<FilmInfoVO> getFilmList() {
        return filmList;
    }

    public void setFilmList(List<FilmInfoVO> filmList) {
        this.filmList = filmList;
    }
}
