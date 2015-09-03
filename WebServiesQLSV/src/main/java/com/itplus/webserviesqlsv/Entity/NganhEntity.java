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
public class NganhEntity {
    String maNganh = "";
    String tenNganh = "";

    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }

    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }
    @Override
    public NganhEntity clone() throws CloneNotSupportedException {
        return (NganhEntity) super.clone();
    }
}
