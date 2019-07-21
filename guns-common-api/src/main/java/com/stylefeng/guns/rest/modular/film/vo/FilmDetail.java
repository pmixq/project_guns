package com.stylefeng.guns.rest.modular.film.vo;

import java.io.Serializable;

/**
 * @Author WPM
 * @Create 2019/7/17 19:43
 **/
public class FilmDetail implements Serializable {

    private static final long serialVersionUID = -5569486148312115444L;

    private String filmEnName;
    private String filmId;
    private String filmName;
    private String imgAddress;
    private String info01;
    private String info02;
    private String info03;
    private FilmInfo info04;
    private String score;
    private Integer scoreNum;
    private Integer totalBox;

    public String getFilmEnName() {
        return filmEnName;
    }

    public void setFilmEnName(String filmEnName) {
        this.filmEnName = filmEnName;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public String getInfo01() {
        return info01;
    }

    public void setInfo01(String info01) {
        this.info01 = info01;
    }

    public String getInfo02() {
        return info02;
    }

    public void setInfo02(String info02) {
        this.info02 = info02;
    }

    public String getInfo03() {
        return info03;
    }

    public void setInfo03(String info03) {
        this.info03 = info03;
    }

    public FilmInfo getInfo04() {
        return info04;
    }

    public void setInfo04(FilmInfo info04) {
        this.info04 = info04;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(Integer scoreNum) {
        this.scoreNum = scoreNum;
    }

    public Integer getTotalBox() {
        return totalBox;
    }

    public void setTotalBox(Integer totalBox) {
        this.totalBox = totalBox;
    }
}
