/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Dung NT
 */
public class LopMonHocEntity implements Serializable, Cloneable {

    private int idLopMonHoc;
    private String MaMonHoc;
    private String MaLop;
    private String MaGioHoc;
    private String MaPhong;
    private Date NgayBatDauHoc;
    private Date NgayNghiDKBatDau;
    private Date NgayNghiDKKetThuc;
    private String TenMonHoc;
    private String NgayHoc;
    private int SoGio;
    private String TenLop;
    private String ThoiGian;
    private String TenPhongHoc;
    private String NgayBatDauHocView;
    private String NgayNghiDKBatDauView;
    private String NgayNghiDKKetThucView;
    private String GiangVien;

    @Override
    public LopMonHocEntity clone() throws CloneNotSupportedException {
        return (LopMonHocEntity) super.clone();
    }

    public String getGiangVien() {
        return GiangVien;
    }

    public void setGiangVien(String GiangVien) {
        this.GiangVien = GiangVien;
    }

    public String getNgayBatDauHocView() {
        return NgayBatDauHocView;
    }

    public void setNgayBatDauHocView(String NgayBatDauHocView) {
        this.NgayBatDauHocView = NgayBatDauHocView;
    }

    public String getNgayNghiDKBatDauView() {
        if (NgayNghiDKBatDauView.equals("01/01/1900")) {
            return "không";
        } else {
            return NgayNghiDKBatDauView;
        }

    }

    public void setNgayNghiDKBatDauView(String NgayNghiDKBatDauView) {
        this.NgayNghiDKBatDauView = NgayNghiDKBatDauView;
    }

    public String getNgayNghiDKKetThucView() {
        if (NgayNghiDKKetThucView.equals("01/01/1900")) {
            return "Không";
        } else {
            return NgayNghiDKKetThucView;
        }

    }

    public void setNgayNghiDKKetThucView(String NgayNghiDKKetThucView) {
        this.NgayNghiDKKetThucView = NgayNghiDKKetThucView;
    }

    public String getTenMonHoc() {
        return TenMonHoc;
    }

    public void setTenMonHoc(String TenMonHoc) {
        this.TenMonHoc = TenMonHoc;
    }

    public String getNgayHoc() {
        return NgayHoc;
    }

    public void setNgayHoc(String NgayHoc) {
        this.NgayHoc = NgayHoc;
    }

    public int getSoGio() {
        return SoGio;
    }

    public void setSoGio(int SoGio) {
        this.SoGio = SoGio;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String TenLop) {
        this.TenLop = TenLop;
    }

    public String getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(String ThoiGian) {
        this.ThoiGian = ThoiGian;
    }

    public String getTenPhongHoc() {
        return TenPhongHoc;
    }

    public void setTenPhongHoc(String TenPhongHoc) {
        this.TenPhongHoc = TenPhongHoc;
    }

    public int getIdLopMonHoc() {
        return idLopMonHoc;
    }

    public void setIdLopMonHoc(int idLopMonHoc) {
        this.idLopMonHoc = idLopMonHoc;
    }

    public String getMaMonHoc() {
        return MaMonHoc;
    }

    public void setMaMonHoc(String MaMonHoc) {
        this.MaMonHoc = MaMonHoc;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public String getMaGioHoc() {
        return MaGioHoc;
    }

    public void setMaGioHoc(String MaGioHoc) {
        this.MaGioHoc = MaGioHoc;
    }

    public String getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(String MaPhong) {
        this.MaPhong = MaPhong;
    }

    public Date getNgayBatDauHoc() {
        return NgayBatDauHoc;
    }

    public void setNgayBatDauHoc(Date NgayBatDauHoc) {
        this.NgayBatDauHoc = NgayBatDauHoc;
    }

    public Date getNgayNghiDKBatDau() {
        return NgayNghiDKBatDau;
    }

    public void setNgayNghiDKBatDau(Date NgayNghiDKBatDau) {
        this.NgayNghiDKBatDau = NgayNghiDKBatDau;
    }

    public Date getNgayNghiDKKetThuc() {
        return NgayNghiDKKetThuc;
    }

    public void setNgayNghiDKKetThuc(Date NgayNghiDKKetThuc) {
        this.NgayNghiDKKetThuc = NgayNghiDKKetThuc;
    }

}
