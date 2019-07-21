package com.stylefeng.guns.rest.modular.film.vo;

import java.io.Serializable;

/**
 * @Author WPM
 * @Create 2019/7/18 19:22
 **/
public class SourceInfo implements Serializable {
    private static final long serialVersionUID = 4323238150493691463L;

    private Integer sourceId;

    private String sourceName;

    private boolean active;

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
