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
public class SinhVienEntity implements Serializable {

    private String MaSinhVien = "";
    private String TenSinhVien = "";
    private Date NgaySinh;
    private String NgaySinhView;
    private String GioiTinh;
    private String SDT;
    private String DiaChi;
    private String QueQuan;
    private String Email;
    private String MaLop;
    private String MatKhau;
    private String TenLop;
    private String NamNhapHoc = "";
    private String MaKhoaHoc = "";
    private String TenKhoaHoc = "";

    public String getNgaySinhView() {
        return NgaySinhView;
    }

    public void setNgaySinhView(String NgaySinhView) {
        this.NgaySinhView = NgaySinhView;
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

    public String getTenKhoaHoc() {
        return TenKhoaHoc;
    }

    public void setTenKhoaHoc(String TenKhoaHoc) {
        this.TenKhoaHoc = TenKhoaHoc;
    }

    public String getMaSinhVien() {
        return MaSinhVien;
    }

    public void setMaSinhVien(String MaSinhVien) {
        this.MaSinhVien = MaSinhVien;
    }

    public String getTenSinhVien() {
        return TenSinhVien;
    }

    public void setTenSinhVien(String TenSinhVien) {
        this.TenSinhVien = TenSinhVien;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public void setQueQuan(String QueQuan) {
        this.QueQuan = QueQuan;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getMaLop() {
        return MaLop;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String TenLop) {
        this.TenLop = TenLop;
    }

}
