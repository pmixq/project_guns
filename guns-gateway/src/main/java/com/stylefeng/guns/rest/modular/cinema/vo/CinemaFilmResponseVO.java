package com.stylefeng.guns.rest.modular.cinema.vo;



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
