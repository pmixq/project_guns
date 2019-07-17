package com.stylefeng.guns.rest.common.persistence.model.bean;


import com.stylefeng.guns.rest.common.persistence.model.bean.Area;
import com.stylefeng.guns.rest.common.persistence.model.bean.Brand;
import com.stylefeng.guns.rest.common.persistence.model.bean.Halltype;

import java.util.List;

public class ConditionInfo {
    List<Area> areaList;
    List<Brand> brandList;
    List<Halltype> halltypeList;

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public List<Brand> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<Brand> brandList) {
        this.brandList = brandList;
    }

    public List<Halltype> getHalltypeList() {
        return halltypeList;
    }

    public void setHalltypeList(List<Halltype> halltypeList) {
        this.halltypeList = halltypeList;
    }
}
