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
public class LopMonHocEntity {
    private String MaMonHoc = "";
    private String MaLop = "";
    private String MaGioHoc = "";
    private String MaPhongHoc = "";
    private String MaHocKy = "";

    public String getMaMonHoc() {
        return MaMonHoc;
    }

    public void setMaMonHoc(String MaMonHoc) {
        this.MaMonHoc = MaMonHoc;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public String getMaGioHoc() {
        return MaGioHoc;
    }

    public void setMaGioHoc(String MaGioHoc) {
        this.MaGioHoc = MaGioHoc;
    }

    public String getMaPhongHoc() {
        return MaPhongHoc;
    }

    public void setMaPhongHoc(String MaPhongHoc) {
        this.MaPhongHoc = MaPhongHoc;
    }

    public String getMaHocKy() {
        return MaHocKy;
    }

    public void setMaHocKy(String MaHocKy) {
        this.MaHocKy = MaHocKy;
    }

    
    
    @Override
    public LopMonHocEntity clone() throws CloneNotSupportedException {
        return (LopMonHocEntity) super.clone();
    }
}
