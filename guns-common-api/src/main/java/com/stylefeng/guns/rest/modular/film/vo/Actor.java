package com.stylefeng.guns.rest.modular.film.vo;

import java.io.Serializable;

/**
 * @Author WPM
 * @Create 2019/7/17 20:05
 **/
public class Actor implements Serializable {

    private static final long serialVersionUID = -4955737917129482908L;

    private String directorName;
    private String imgAddress;
    private String roleName;

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
