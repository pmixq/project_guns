package com.stylefeng.guns.rest.modular.cinema.vo;

import java.io.Serializable;

public class HallTypeVO implements Serializable {
    private Integer halltypeId;
    private String halltypeName;
    private Boolean active;

    public Integer getHalltypeId() {
        return halltypeId;
    }

    public void setHalltypeId(Integer halltypeId) {
        this.halltypeId = halltypeId;
    }

    public String getHalltypeName() {
        return halltypeName;
    }

    public void setHalltypeName(String halltypeName) {
        this.halltypeName = halltypeName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
