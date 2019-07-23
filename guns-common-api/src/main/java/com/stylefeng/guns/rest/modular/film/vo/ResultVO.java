package com.stylefeng.guns.rest.modular.film.vo;



import java.io.Serializable;
import java.lang.annotation.Annotation;


public class ResultVO implements Serializable {

    private static final long serialVersionUID = -8161748837609234457L;

    private int status;
    private String imgPre;
    private int nowPage;
    private int totalPage;
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImgPre() {
        return imgPre;
    }

    public void setImgPre(String imgPre) {
        this.imgPre = imgPre;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
