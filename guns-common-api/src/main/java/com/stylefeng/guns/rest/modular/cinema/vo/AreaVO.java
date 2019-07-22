package com.stylefeng.guns.rest.modular.cinema.vo;

import java.io.Serializable;

public class AreaVO implements Serializable {
    private Integer areaId;
    private String areaName;
    private Boolean active;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
