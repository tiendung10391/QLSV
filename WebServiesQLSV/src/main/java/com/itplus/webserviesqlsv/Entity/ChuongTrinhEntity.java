/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.Entity;

import java.io.Serializable;

/**
 *
 * @author Dung NT
 */
public class ChuongTrinhEntity implements Serializable {

    private String MaMon;
    private String TenMon;
    private int SoGio;
    private String GhiChu;
    private String MaHocKy;
    private String TenHocKy;
    private String MaKhoaHoc;

    public String getMaHocKy() {
        return MaHocKy;
    }

    public void setMaHocKy(String MaHocKy) {
        this.MaHocKy = MaHocKy;
    }

    public String getTenHocKy() {
        return TenHocKy;
    }

    public void setTenHocKy(String TenHocKy) {
        this.TenHocKy = TenHocKy;
    }

    public String getMaKhoaHoc() {
        return MaKhoaHoc;
    }

    public void setMaKhoaHoc(String MaKhoaHoc) {
        this.MaKhoaHoc = MaKhoaHoc;
    }

    public String getMaMon() {
        return MaMon;
    }

    public void setMaMon(String MaMon) {
        this.MaMon = MaMon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String TenMon) {
        this.TenMon = TenMon;
    }

    public int getSoGio() {
        return SoGio;
    }

    public void setSoGio(int SoGio) {
        this.SoGio = SoGio;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

}
