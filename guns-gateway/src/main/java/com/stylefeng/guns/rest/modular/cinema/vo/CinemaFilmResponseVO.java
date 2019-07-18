package com.stylefeng.guns.rest.modular.cinema.vo;

import com.stylefeng.guns.rest.modular.vo.CinemaInfoVO;
import com.stylefeng.guns.rest.modular.vo.FilmInfoVO;
import com.stylefeng.guns.rest.modular.vo.HallInfoVO;

public class CinemaFilmResponseVO {

    private CinemaInfoVO cinemaInfo;
    private FilmInfoVO filmInfo;
    private HallInfoVO hallInfo;

    public CinemaInfoVO getCinemaInfo() {
        return cinemaInfo;
    }

    public void setCinemaInfo(CinemaInfoVO cinemaInfo) {
        this.cinemaInfo = cinemaInfo;
    }

    public FilmInfoVO getFilmInfo() {
        return filmInfo;
    }

    public void setFilmInfo(FilmInfoVO filmInfo) {
        this.filmInfo = filmInfo;
    }

    public HallInfoVO getHallInfo() {
        return hallInfo;
    }

    public void setHallInfo(HallInfoVO hallInfo) {
        this.hallInfo = hallInfo;
    }
}
