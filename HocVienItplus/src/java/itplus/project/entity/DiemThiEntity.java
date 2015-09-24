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
public class DiemThiEntity implements Serializable {

    private int id_diem;
    private String TenMon;
    private String MaMon;
    private String MaHocKy;
    private int DiemLan1;
    private int DiemLan2;
    private int DiemLan3;
    private String TrangThai;
    private String TenHocKy;

    public DiemThiEntity() {
    }

    public DiemThiEntity(String TenMon, int DiemLan1, int DiemLan2, int DiemLan3, String TrangThai) {
        this.TenMon = TenMon;
        this.DiemLan1 = DiemLan1;
        this.DiemLan2 = DiemLan2;
        this.DiemLan3 = DiemLan3;
        this.TrangThai = TrangThai;
    }
    
    

    public String getTenHocKy() {
        return TenHocKy;
    }

    public void setTenHocKy(String TenHocKy) {
        this.TenHocKy = TenHocKy;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public int getId_diem() {
        return id_diem;
    }

    public void setId_diem(int id_diem) {
        this.id_diem = id_diem;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String TenMon) {
        this.TenMon = TenMon;
    }

    public String getMaMon() {
        return MaMon;
    }

    public void setMaMon(String MaMon) {
        this.MaMon = MaMon;
    }

    public String getMaHocKy() {

        return MaHocKy;
    }

    public void setMaHocKy(String MaHocKy) {
        this.MaHocKy = MaHocKy;
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
