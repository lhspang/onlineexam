package com.sen.onlineexam.pojo;

import java.io.Serializable;

/**
 * @author 
 * 
 */
public class Protype implements Serializable {
    private Integer protypeId;

    private String protypeName;

    private Integer protypeKeynum;

    private static final long serialVersionUID = 1L;

    public Protype() {
    }

    public Protype(Integer protypeId, String protypeName, Integer protypeKeynum) {
        this.protypeId = protypeId;
        this.protypeName = protypeName;
        this.protypeKeynum = protypeKeynum;
    }

    public Integer getProtypeId() {
        return protypeId;
    }

    public void setProtypeId(Integer protypeId) {
        this.protypeId = protypeId;
    }

    public String getProtypeName() {
        return protypeName;
    }

    public void setProtypeName(String protypeName) {
        this.protypeName = protypeName;
    }

    public Integer getProtypeKeynum() {
        return protypeKeynum;
    }

    public void setProtypeKeynum(Integer protypeKeynum) {
        this.protypeKeynum = protypeKeynum;
    }

    @Override
    public String toString() {
        return "Protype{" +
                "protypeId=" + protypeId +
                ", protypeName='" + protypeName + '\'' +
                ", protypeKeynum=" + protypeKeynum +
                '}';
    }
}