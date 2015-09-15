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
public class DiemEntity implements Serializable, Cloneable {

    private int idDiemSinhVien;
    private String maSinhVien;
    private String TenSinhVien;
    private int DiemLan1;
    private int DiemLan2;
    private int DiemLan3;
    private String MaLop;
    private String MaMon;

    @Override
    public DiemEntity clone() throws CloneNotSupportedException {
        return (DiemEntity) super.clone();
    }

    public int getIdDiemSinhVien() {
        return idDiemSinhVien;
    }

    public void setIdDiemSinhVien(int idDiemSinhVien) {
        this.idDiemSinhVien = idDiemSinhVien;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public String getMaMon() {
        return MaMon;
    }

    public void setMaMon(String MaMon) {
        this.MaMon = MaMon;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getTenSinhVien() {
        return TenSinhVien;
    }

    public void setTenSinhVien(String TenSinhVien) {
        this.TenSinhVien = TenSinhVien;
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

}
