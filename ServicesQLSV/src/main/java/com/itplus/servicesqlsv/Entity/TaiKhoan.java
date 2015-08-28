/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.servicesqlsv.Entity;

/**
 *
 * @author kunph_000
 */
public class TaiKhoan {
    String maSV = "";
    String matKhau = "";

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    @Override
    public TaiKhoan clone() throws CloneNotSupportedException {
        return (TaiKhoan) super.clone();
    }
}
