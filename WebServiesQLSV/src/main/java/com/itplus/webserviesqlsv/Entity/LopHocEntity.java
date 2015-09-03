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
public class LopHocEntity {

    private String MaLop;
    private String TenLop;
    private String HeDaoTao;
    private String NamNhapHoc;
    private String MaNganh;
    private String MaKhoaHoc;

    public String getMaNganh() {
        return MaNganh;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String TenLop) {
        this.TenLop = TenLop;
    }

    public String getHeDaoTao() {
        return HeDaoTao;
    }

    public void setHeDaoTao(String HeDaoTao) {
        this.HeDaoTao = HeDaoTao;
    }

    public String getNamNhapHoc() {
        return NamNhapHoc;
    }

    public void setNamNhapHoc(String NamNhapHoc) {
        this.NamNhapHoc = NamNhapHoc;
    }

    public String getMaKhoaHoc() {
        return MaKhoaHoc;
    }

    public void setMaKhoaHoc(String MaKhoaHoc) {
        this.MaKhoaHoc = MaKhoaHoc;
    }

    public void setMaNganh(String MaNganh) {
        this.MaNganh = MaNganh;
    }

}
