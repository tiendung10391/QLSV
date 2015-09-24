/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.bean;

import itplus.project.entity.DiemThiEntity;
import itplus.project.entity.HocKyEntity;
import itplus.project.model.DiemThiModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dung NT
 */
public class DiemBean implements Serializable {

    /**
     * Creates a new instance of DiemBean
     */
    private DiemThiEntity diemThiEntity;
    ArrayList<DiemThiEntity> arrDiemThiKhoaHoc;
    ArrayList<DiemThiEntity> arrDiemThiHocKy;
    List<HocKyEntity> arrHocKy;
    private DiemThiModel diemThiModel;

    List<HocKyEntity> listHocKy;

    public DiemBean() {
        diemThiEntity = new DiemThiEntity();
        diemThiModel = new DiemThiModel();
        getAllDiemThiHocKy();
        getAllDiemThiKhoaHoc();
    }

    public void getAllDiemThiHocKy() {
        try {
            arrDiemThiHocKy = new ArrayList<DiemThiEntity>();
            HttpSession session = SessionBean.newSession(false);
            String MaSV = (String) session.getAttribute("username");
            System.out.println("Diem Thi Khoa Hoc:");
            System.out.println("MaSV: " + MaSV);
            arrDiemThiHocKy = diemThiModel.getDiemHocKy(MaSV);

        } catch (Exception ex) {
            System.out.println("ex: " + ex);
        }

    }

    // tong ket diem cua ca khoa hoc
    public void getAllDiemThiKhoaHoc() {
        try {

            HttpSession session = SessionBean.newSession(false);
            String MaSV = (String) session.getAttribute("username");

            // list hoc ky
            arrHocKy = new ArrayList<HocKyEntity>();
            arrHocKy = diemThiModel.getAllHocKy(MaSV);

            // danh sach diem cac mon cua ca khoa hoc
            arrDiemThiKhoaHoc = new ArrayList<DiemThiEntity>();
            arrDiemThiKhoaHoc = diemThiModel.getDiemKhoaHoc(MaSV);

            // tao vong lap for 
            listHocKy = new ArrayList<HocKyEntity>();
            for (int i = 0; i < arrHocKy.size(); i++) {
                HocKyEntity hocKy = new HocKyEntity(arrHocKy.get(i).getTenHocKy());
                for(int j = 0; j < arrDiemThiKhoaHoc.size(); j++){
                    if(arrHocKy.get(i).getMaHocKy().equals(arrDiemThiKhoaHoc.get(j).getMaHocKy())){
                        String TenMon = arrDiemThiKhoaHoc.get(j).getTenMon();
                        int DienLan1 = arrDiemThiKhoaHoc.get(j).getDiemLan1();
                        int DienLan2 = arrDiemThiKhoaHoc.get(j).getDiemLan2();
                        int DienLan3 = arrDiemThiKhoaHoc.get(j).getDiemLan3();
                        String TrangThai = setTrangThai(DienLan1, DienLan2, DienLan3);
                        hocKy.getListDiem().add(new DiemThiEntity(TenMon, DienLan1, DienLan2, DienLan3, TrangThai));
                    }
                }
                listHocKy.add(hocKy);
            }

        } catch (Exception ex) {
            System.out.println("ex: " + ex);
        }
    }
    
    public String setTrangThai(int diem1, int diem2, int diem3) {
        if (diem1 >= 65) {
            return "đạt";
        } else {
            if (diem2 >= 65) {
                return "đạt";
            } else {
                if (diem3 >= 65) {
                    return "đạt";
                } else {
                    return "không đạt";
                }
            }
        }

    }

    public List<HocKyEntity> getArrHocKy() {
        return arrHocKy;
    }

    public void setArrHocKy(List<HocKyEntity> arrHocKy) {
        this.arrHocKy = arrHocKy;
    }

    public List<HocKyEntity> getListHocKy() {
        return listHocKy;
    }

    public void setListHocKy(List<HocKyEntity> listHocKy) {
        this.listHocKy = listHocKy;
    }

    public void setArrHocKy(ArrayList<HocKyEntity> arrHocKy) {
        this.arrHocKy = arrHocKy;
    }

    public ArrayList<DiemThiEntity> getArrDiemThiHocKy() {
        return arrDiemThiHocKy;
    }

    public void setArrDiemThiHocKy(ArrayList<DiemThiEntity> arrDiemThiHocKy) {
        this.arrDiemThiHocKy = arrDiemThiHocKy;
    }

    public void getDiemKhoaHoc() {

    }

    public DiemThiEntity getDiemThiEntity() {
        return diemThiEntity;
    }

    public void setDiemThiEntity(DiemThiEntity diemThiEntity) {
        this.diemThiEntity = diemThiEntity;
    }

    public ArrayList<DiemThiEntity> getArrDiemThiKhoaHoc() {
        return arrDiemThiKhoaHoc;
    }

    public void setArrDiemThiKhoaHoc(ArrayList<DiemThiEntity> arrDiemThiKhoaHoc) {
        this.arrDiemThiKhoaHoc = arrDiemThiKhoaHoc;
    }

    public DiemThiModel getDiemThiModel() {
        return diemThiModel;
    }

    public void setDiemThiModel(DiemThiModel diemThiModel) {
        this.diemThiModel = diemThiModel;
    }

}
