package com.stylefeng.guns.rest.modular.film.vo;

import java.io.Serializable;

/**
 * @Author WPM
 * @Create 2019/7/18 19:20
 **/


public class CatInfo implements Serializable {

    private static final long serialVersionUID = -2377784838536182175L;

    private Integer catId;

    private String catName;

    private boolean active;

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
