/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.Entity;

/**
 *
 * @author kunph_000
 */
public class MonHoc_HocKyEntity {
    private String maMH ="";
    private String maLop = "";
    private String maHocky = "";

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getMaHocky() {
        return maHocky;
    }

    public void setMaHocky(String maHocky) {
        this.maHocky = maHocky;
    }
     @Override
    public MonHoc_HocKyEntity clone() throws CloneNotSupportedException {
        return (MonHoc_HocKyEntity) super.clone();
    }
}
