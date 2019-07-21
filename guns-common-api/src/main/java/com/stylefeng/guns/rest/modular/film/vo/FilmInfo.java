package com.stylefeng.guns.rest.modular.film.vo;

import java.io.Serializable;

/**
 * @Author WPM
 * @Create 2019/7/17 19:48
 **/
public class FilmInfo implements Serializable {

    private static final long serialVersionUID = -1433731583646576291L;

    private Object actors;
    private String biopgraphy;
    private String filmId;
    private Object imgVO;

    public Object getActors() {
        return actors;
    }

    public void setActors(Object actors) {
        this.actors = actors;
    }

    public String getBiopgraphy() {
        return biopgraphy;
    }

    public void setBiopgraphy(String biopgraphy) {
        this.biopgraphy = biopgraphy;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public Object getImgVO() {
        return imgVO;
    }

    public void setImgVO(Object imgVO) {
        this.imgVO = imgVO;
    }
}
