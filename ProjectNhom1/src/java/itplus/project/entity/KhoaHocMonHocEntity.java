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
public class KhoaHocMonHocEntity implements Serializable, Cloneable {

    private int idKhoaHocMonHoc;
    private String MaKhoaHoc;
    private String TenKhoaHoc;
    private String MaMonHoc;
    private String TenMonHoc;
    private String MaHocKy;
    private String TenHocKy;
    private int ThuTu;

    @Override
    public KhoaHocMonHocEntity clone() throws CloneNotSupportedException {
        return (KhoaHocMonHocEntity) super.clone();
    }

    public int getIdKhoaHocMonHoc() {
        return idKhoaHocMonHoc;
    }

    public void setIdKhoaHocMonHoc(int idKhoaHocMonHoc) {
        this.idKhoaHocMonHoc = idKhoaHocMonHoc;
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

    public int getThuTu() {
        return ThuTu;
    }

    public void setThuTu(int ThuTu) {
        this.ThuTu = ThuTu;
    }

}
