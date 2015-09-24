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

    List<DiemThiEntity> listDiem;
    List<ChuongTrinhEntity> listChuongTrinh;

    public List<ChuongTrinhEntity> getListChuongTrinh() {
        return listChuongTrinh;
    }

    public void setListChuongTrinh(List<ChuongTrinhEntity> listChuongTrinh) {
        this.listChuongTrinh = listChuongTrinh;
    }

    
    
    public HocKyEntity() {
        listDiem = new ArrayList<DiemThiEntity>();
    }

    public HocKyEntity(String TenHocKy) {
        this.TenHocKy = TenHocKy;
        listChuongTrinh = new ArrayList<ChuongTrinhEntity>();
        listDiem = new ArrayList<DiemThiEntity>();
    }

    public List<DiemThiEntity> getListDiem() {
        return listDiem;
    }

    public void setListDiem(List<DiemThiEntity> listDiem) {
        this.listDiem = listDiem;
    }

//    public List<DiemThiEntity> getListHocKy() {
//        return listDiem;
//    }
//
//    public void setListHocKy(List<DiemThiEntity> listDiem) {
//        this.listDiem = listDiem;
//    }
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
    
    public int getDiemTrungBinh(){
        int diemTB = 0;
        int soMon = 0;
        for(int i = 0; i< listDiem.size(); i++){
            soMon++;
            int diem1 = listDiem.get(i).getDiemLan1();
            int diem2 = listDiem.get(i).getDiemLan2();
            int diem3 = listDiem.get(i).getDiemLan3();
            
            diemTB += getDiemThiCaoNhat(diem1, diem2, diem3);
        }
        
        diemTB = diemTB/soMon;
        
        return diemTB;
    }
    
    public String getXepLoai(){
        String xepLoai = "Xếp loại: ";
        float diemTB = 0f;
        int soMon = 0;
        boolean checkXuatSac = false;
        boolean checkGioi = false;
        for(int i = 0; i< listDiem.size(); i++){
            soMon++;
            int diem1 = listDiem.get(i).getDiemLan1();
            int diem2 = listDiem.get(i).getDiemLan2();
            int diem3 = listDiem.get(i).getDiemLan3();
            
            diemTB += getDiemThiCaoNhat(diem1, diem2, diem3);
        }
        
        diemTB = diemTB/soMon;
        if(diemTB >= 91 ){
            xepLoai += "Xuất sắc";
        }else if(diemTB >= 81 && diemTB <91){
            xepLoai += "Giỏi";
        }else if(diemTB >= 71 && diemTB < 81){
            xepLoai += "Khá";
        }else if(diemTB >= 65 && diemTB < 70){
            xepLoai += "Đạt";
        }else{
            xepLoai += "Không đạt";
        }
        
        return xepLoai;
    }
    
    public int getDiemThiCaoNhat(int diem1, int diem2, int diem3){
        int diem = 0;
        if(diem1 >= 65){
            return diem = diem1;
        }else{
            if(diem2 >= 65){
                return diem = diem2;
            }else{
                return diem = diem3;
            }
        }
    }

}
