package com.stylefeng.guns.rest.modular.film.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Author WPM
 * @Create 2019/7/18 19:47
 **/
public class ConditionInfo implements Serializable {

    private static final long serialVersionUID = 2476431241746096875L;

    private List<CatInfo> catInfo;

    private List<SourceInfo> sourceInfo;

    private List<YearInfo> yearInfo;

    public List<CatInfo> getCatInfo() {
        return catInfo;
    }

    public void setCatInfo(List<CatInfo> catInfo) {
        this.catInfo = catInfo;
    }

    public List<SourceInfo> getSourceInfo() {
        return sourceInfo;
    }

    public void setSourceInfo(List<SourceInfo> sourceInfo) {
        this.sourceInfo = sourceInfo;
    }

    public List<YearInfo> getYearInfo() {
        return yearInfo;
    }

    public void setYearInfo(List<YearInfo> yearInfo) {
        this.yearInfo = yearInfo;
    }
}
