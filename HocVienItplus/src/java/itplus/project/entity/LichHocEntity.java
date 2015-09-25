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
public class LichHocEntity implements Serializable{
    private String PhongHoc;
    private String GioHoc;
    private String MonHoc;
    private String NgayHoc;

    public String getPhongHoc() {
        return PhongHoc;
    }

    public void setPhongHoc(String PhongHoc) {
        this.PhongHoc = PhongHoc;
    }

    public String getGioHoc() {
        return GioHoc;
    }

    public void setGioHoc(String GioHoc) {
        this.GioHoc = GioHoc;
    }

    public String getMonHoc() {
        return MonHoc;
    }

    public void setMonHoc(String MonHoc) {
        this.MonHoc = MonHoc;
    }

    public String getNgayHoc() {
        return NgayHoc;
    }

    public void setNgayHoc(String NgayHoc) {
        this.NgayHoc = NgayHoc;
    }
    
    
}
