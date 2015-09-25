/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.Entity;

/**
 *
 * @author Dung NT
 */
public class DiemEntity {

    private String MaMonHoc;
    private String MaSV;
    private int DiemLan1;
    private int Diemlan2;
    private int DiemLan3;

    private String MaHocKy;
    private String HocKy;

    public String getMaHocKy() {
        return MaHocKy;
    }

    public void setMaHocKy(String MaHocKy) {
        this.MaHocKy = MaHocKy;
    }

    public String getHocKy() {
        return HocKy;
    }

    public void setHocKy(String HocKy) {
        this.HocKy = HocKy;
    }

    public String getMaMonHoc() {
        return MaMonHoc;
    }

    public void setMaMonHoc(String MaMonHoc) {
        this.MaMonHoc = MaMonHoc;
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String MaSV) {
        this.MaSV = MaSV;
    }

    public int getDiemLan1() {
        return DiemLan1;
    }

    public void setDiemLan1(int DiemLan1) {
        this.DiemLan1 = DiemLan1;
    }

    public int getDiemlan2() {
        return Diemlan2;
    }

    public void setDiemlan2(int Diemlan2) {
        this.Diemlan2 = Diemlan2;
    }

    public int getDiemLan3() {
        return DiemLan3;
    }

    public void setDiemLan3(int DiemLan3) {
        this.DiemLan3 = DiemLan3;
    }

}
