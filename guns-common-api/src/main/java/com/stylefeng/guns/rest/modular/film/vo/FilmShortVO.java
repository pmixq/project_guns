package com.stylefeng.guns.rest.modular.film.vo;


import java.io.Serializable;


public class FilmShortVO implements Serializable{

    private static final long serialVersionUID = -3345880689021890606L;

    private int filmId;
    private int filmType;
    private String imgAddress;
    private String filmName;
    private String filmScore;

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getFilmType() {
        return filmType;
    }

    public void setFilmType(int filmType) {
        this.filmType = filmType;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmScore() {
        return filmScore;
    }

    public void setFilmScore(String filmScore) {
        this.filmScore = filmScore;
    }
}
