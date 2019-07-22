package com.stylefeng.guns.rest.modular.cinema.vo;

import java.io.Serializable;

public class CinemaQueryVO implements Serializable {
    private Integer brandId=99;
    private Integer areaId=99;
    private Integer hallType=99;
    private Integer pageSize=12;
    private Integer nowPage=1;

    public CinemaQueryVO() {
    }

    public CinemaQueryVO(Integer brandId, Integer areaId, Integer hallType, Integer pageSize, Integer nowPage) {
        this.brandId = brandId;
        this.areaId = areaId;
        this.hallType = hallType;
        this.pageSize = pageSize;
        this.nowPage = nowPage;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getHallType() {
        return hallType;
    }

    public void setHallType(Integer hallType) {
        this.hallType = hallType;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getNowPage() {
        return nowPage;
    }

    public void setNowPage(Integer nowPage) {
        this.nowPage = nowPage;
    }
}
