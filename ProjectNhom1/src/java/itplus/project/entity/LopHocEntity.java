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
public class LopHocEntity implements Serializable, Cloneable {

    private String MaLop;
    private String TenLop;
    private String NamNhapHoc;
    private String MaKhoaHoc;
    private String MaNganh;
    private String TenKhoaHoc;
    private String TenNganh;
    private String HeDaoTao;

    public LopHocEntity Clone() {
        try {
            return (LopHocEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public LopHocEntity clone() throws CloneNotSupportedException {
        return (LopHocEntity) super.clone();
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

    public String getMaNganh() {
        return MaNganh;
    }

    public void setMaNganh(String MaNganh) {
        this.MaNganh = MaNganh;
    }

    public String getTenKhoaHoc() {
        return TenKhoaHoc;
    }

    public void setTenKhoaHoc(String TenKhoaHoc) {
        this.TenKhoaHoc = TenKhoaHoc;
    }

    public String getTenNganh() {
        return TenNganh;
    }

    public void setTenNganh(String TenNganh) {
        this.TenNganh = TenNganh;
    }

    public String getHeDaoTao() {
        return HeDaoTao;
    }

    public void setHeDaoTao(String HeDaoTao) {
        this.HeDaoTao = HeDaoTao;
    }

}
