package com.stylefeng.guns.rest.modular.cinema.vo;

import java.io.Serializable;
import java.util.List;

public class FilmInfoVO implements Serializable {
    private Integer filmId;
    private String filmName;
    private Integer cinemaId;
    private String filmLength;
    private String filmType;
    private String filmCats;
    private String actors;
    private String imgAddress;
    private List<FilmFieldVO> filmFields;

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }



    public String getFilmLength() {
        return filmLength;
    }

    public void setFilmLength(String filmLength) {
        this.filmLength = filmLength;
    }

    public String getFilmType() {
        return filmType;
    }

    public void setFilmType(String filmType) {
        this.filmType = filmType;
    }

    public String getFilmCats() {
        return filmCats;
    }

    public void setFilmCats(String filmCats) {
        this.filmCats = filmCats;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public List<FilmFieldVO> getFilmFields() {
        return filmFields;
    }

    public void setFilmFields(List<FilmFieldVO> filmFields) {
        this.filmFields = filmFields;
    }
}
