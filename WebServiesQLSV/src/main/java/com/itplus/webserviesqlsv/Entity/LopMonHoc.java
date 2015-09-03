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
public class LopMonHoc {
    String maMH = "";
    String maLop = "";
    String maGiohoc = "";
    String tenPhong = "";
    String maHocky = "";

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

    public String getMaGiohoc() {
        return maGiohoc;
    }

    public void setMaGiohoc(String maGiohoc) {
        this.maGiohoc = maGiohoc;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getMaHocky() {
        return maHocky;
    }

    public void setMaHocky(String maHocky) {
        this.maHocky = maHocky;
    }
    @Override
    public LopMonHoc clone() throws CloneNotSupportedException {
        return (LopMonHoc) super.clone();
    }
}
