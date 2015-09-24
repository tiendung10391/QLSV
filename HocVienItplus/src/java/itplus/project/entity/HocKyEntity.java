/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dung NT
 */
public class HocKyEntity implements Serializable {

    private String MaHocKy;
    private String TenHocKy;
    
    private List<DiemThiEntity> listDiem;

    public HocKyEntity() {
        listDiem = new ArrayList<DiemThiEntity>();
    }

    public HocKyEntity(String TenHocKy) {
        this.TenHocKy = TenHocKy;
        listDiem = new ArrayList<DiemThiEntity>();
    }

    
    
    public List<DiemThiEntity> getListHocKy() {
        return listDiem;
    }

    public void setListHocKy(List<DiemThiEntity> listDiem) {
        this.listDiem = listDiem;
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

}
