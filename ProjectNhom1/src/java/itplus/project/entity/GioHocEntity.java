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
public class GioHocEntity implements Serializable {
    private String MaGioHoc;
    private String ThoiGian;

    public String getMaGioHoc() {
        return MaGioHoc;
    }

    public void setMaGioHoc(String MaGioHoc) {
        this.MaGioHoc = MaGioHoc;
    }

    public String getThoiGian() {
        return ThoiGian;
    }

    public void setThoiGian(String ThoiGian) {
        this.ThoiGian = ThoiGian;
    }
    
    
}
