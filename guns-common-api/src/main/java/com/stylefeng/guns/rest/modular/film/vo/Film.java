package com.stylefeng.guns.rest.modular.film.vo;

import java.util.Date;

public class Film {
    private int boxNum;
    private int expectNum;
    private int filmCats;
    private int filmId;
    private int filmLength;
    private String filmName;
    private String filmScore;
    private int filmType;
    private String imgAddress;
    private String score;
    private Date showTime;

    public int getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(int boxNum) {
        this.boxNum = boxNum;
    }

    public int getExpectNum() {
        return expectNum;
    }

    public void setExpectNum(int expectNum) {
        this.expectNum = expectNum;
    }

    public int getFilmCats() {
        return filmCats;
    }

    public void setFilmCats(int filmCats) {
        this.filmCats = filmCats;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getFilmLength() {
        return filmLength;
    }

    public void setFilmLength(int filmLength) {
        this.filmLength = filmLength;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }
}
