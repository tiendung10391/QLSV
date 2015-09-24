/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itplus.webserviesqlsv.Entity;

/**
 *
 * @author Administrator
 */
public class AdChuongTrinhEntity {
    private String MaMonHoc;
    private String TenMonHoc;
    private int SoGio;
    private String GhiChu;
   

    public String getMaMonHoc() {
        return MaMonHoc;
    }

    public void setMaMonHoc(String MaMonHoc) {
        this.MaMonHoc = MaMonHoc;
    }

    public String getTenMonHoc() {
        return TenMonHoc;
    }

    public void setTenMonHoc(String TenMonHoc) {
        this.TenMonHoc = TenMonHoc;
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
