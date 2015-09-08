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
public class LopHocEntity implements Serializable {

    private String MaLop;
    private String TenLop;
    private String NamNhapHoc;
    private String MaKhoaHoc;


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


}
