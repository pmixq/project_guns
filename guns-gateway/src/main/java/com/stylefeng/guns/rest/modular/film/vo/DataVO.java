package com.stylefeng.guns.rest.modular.film.vo;


import org.springframework.boot.Banner;

import java.util.List;

public class DataVO {
    private List<Banner> banners ;
    private List<StatusFilm> hotFilms ;
    private List<StatusFilm> soonFilms ;
    private List<Film> boxRanking;
    private List<Film> expectRanking;
    private List<Film> top100;

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<StatusFilm> getHotFilms() {
        return hotFilms;
    }

    public void setHotFilms(List<StatusFilm> hotFilms) {
        this.hotFilms = hotFilms;
    }

    public List<StatusFilm> getSoonFilms() {
        return soonFilms;
    }

    public void setSoonFilms(List<StatusFilm> soonFilms) {
        this.soonFilms = soonFilms;
    }

    public List<Film> getBoxRanking() {
        return boxRanking;
    }

    public void setBoxRanking(List<Film> boxRanking) {
        this.boxRanking = boxRanking;
    }

    public List<Film> getExpectRanking() {
        return expectRanking;
    }

    public void setExpectRanking(List<Film> expectRanking) {
        this.expectRanking = expectRanking;
    }

    public List<Film> getTop100() {
        return top100;
    }

    public void setTop100(List<Film> top100) {
        this.top100 = top100;
    }
}
