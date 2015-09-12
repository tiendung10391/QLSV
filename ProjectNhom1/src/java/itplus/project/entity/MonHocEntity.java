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
public class MonHocEntity implements Serializable, Cloneable{
    private  String MaMonHoc;
    private String TenMonHoc;
    private String SoGio;
    private String GhiChu;
    
    @Override
    public MonHocEntity clone() throws CloneNotSupportedException {
        return (MonHocEntity) super.clone();
    }

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

    public String getSoGio() {
        return SoGio;
    }

    public void setSoGio(String SoGio) {
        this.SoGio = SoGio;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
    
}
