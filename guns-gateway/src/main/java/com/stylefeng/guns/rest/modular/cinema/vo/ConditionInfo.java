package com.stylefeng.guns.rest.modular.cinema.vo;


import java.util.List;

public class ConditionInfo {
    List<AreaVO> areaList;
    List<BrandVO> brandList;
    List<HallTypeVO> halltypeList;

    public ConditionInfo(List<AreaVO> areaList, List<BrandVO> brandList, List<HallTypeVO> halltypeList) {
        this.areaList = areaList;
        this.brandList = brandList;
        this.halltypeList = halltypeList;
    }

    public List<AreaVO> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaVO> areaList) {
        this.areaList = areaList;
    }

    public List<BrandVO> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<BrandVO> brandList) {
        this.brandList = brandList;
    }

    public List<HallTypeVO> getHalltypeList() {
        return halltypeList;
    }

    public void setHalltypeList(List<HallTypeVO> halltypeList) {
        this.halltypeList = halltypeList;
    }
}
