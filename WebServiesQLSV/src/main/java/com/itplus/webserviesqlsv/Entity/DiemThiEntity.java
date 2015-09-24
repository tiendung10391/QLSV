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
public class DiemThiEntity implements Serializable {

    private int id_diem;
    private String MaMonHoc;
    private int DiemLan1;
    private int DiemLan2;
    private int DiemLan3;
    private String MaLop;
    private String MaKhoaHoc;
    private String TenKhoaHoc;
    private String MaHocKy;
    private String TenHocKy;
    private String TenMon;

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String TenMon) {
        this.TenMon = TenMon;
    }

    public int getId_diem() {
        return id_diem;
    }

    public void setId_diem(int id_diem) {
        this.id_diem = id_diem;
    }

    public String getMaMonHoc() {
        return MaMonHoc;
    }

    public void setMaMonHoc(String MaMonHoc) {
        this.MaMonHoc = MaMonHoc;
    }

    public int getDiemLan1() {
        return DiemLan1;
    }

    public void setDiemLan1(int DiemLan1) {
        this.DiemLan1 = DiemLan1;
    }

    public int getDiemLan2() {
        return DiemLan2;
    }

    public void setDiemLan2(int DiemLan2) {
        this.DiemLan2 = DiemLan2;
    }

    public int getDiemLan3() {
        return DiemLan3;
    }

    public void setDiemLan3(int DiemLan3) {
        this.DiemLan3 = DiemLan3;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public String getMaKhoaHoc() {
        return MaKhoaHoc;
    }

    public void setMaKhoaHoc(String MaKhoaHoc) {
        this.MaKhoaHoc = MaKhoaHoc;
    }

    public String getTenKhoaHoc() {
        return TenKhoaHoc;
    }

    public void setTenKhoaHoc(String TenKhoaHoc) {
        this.TenKhoaHoc = TenKhoaHoc;
    }

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

}
