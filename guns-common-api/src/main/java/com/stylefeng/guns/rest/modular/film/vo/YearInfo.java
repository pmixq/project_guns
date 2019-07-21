package com.stylefeng.guns.rest.modular.film.vo;

import java.io.Serializable;

/**
 * @Author WPM
 * @Create 2019/7/18 19:23
 **/
public class YearInfo implements Serializable {
    private static final long serialVersionUID = -3516312514811778817L;

    private Integer yearId;

    private String yearName;

    private boolean active;

    public Integer getYearId() {
        return yearId;
    }

    public void setYearId(Integer yearId) {
        this.yearId = yearId;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
