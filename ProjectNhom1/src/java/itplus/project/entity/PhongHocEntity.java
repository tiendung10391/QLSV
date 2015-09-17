/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.entity;

import java.io.Serializable;

/**
 *
 * @author Dung NT
 */
public class PhongHocEntity implements Serializable {
    private String MaPhongHoc;
    private String TenPhongHoc;

    public String getMaPhongHoc() {
        return MaPhongHoc;
    }

    public void setMaPhongHoc(String MaPhongHoc) {
        this.MaPhongHoc = MaPhongHoc;
    }

    public String getTenPhongHoc() {
        return TenPhongHoc;
    }

    public void setTenPhongHoc(String TenPhongHoc) {
        this.TenPhongHoc = TenPhongHoc;
    }
    
    
}
