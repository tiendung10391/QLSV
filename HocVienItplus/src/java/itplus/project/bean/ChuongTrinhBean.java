/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.bean;

import itplus.project.entity.ChuongTrinhEntity;
import itplus.project.entity.DiemThiEntity;
import itplus.project.entity.HocKyEntity;
import itplus.project.entity.KhoaHocEntity;
import itplus.project.model.ChuongTrinhModel;
import itplus.project.model.DiemThiModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dung NT
 */
public class ChuongTrinhBean implements Serializable {

    /**
     * Creates a new instance of ChuongTrinhBean
     */
    private ChuongTrinhEntity chuongTrinhEntity;
    private ChuongTrinhModel chuongTrinhModel;
    
    private ArrayList<ChuongTrinhEntity> arrChuongTrinh;
    private List<HocKyEntity> listHocKy;
    private DiemThiModel diemThiModel;
    List<HocKyEntity> arrHocKy;

    private ArrayList<KhoaHocEntity> arrKhoaHoc = null;
    private String KhoaHoc;
    private Map<String, String> KhoaHocList = new HashMap<String, String>();

    public ChuongTrinhBean() {
        System.out.println("chay vao chuong trinh bean");
        chuongTrinhEntity = new ChuongTrinhEntity();
        chuongTrinhModel = new ChuongTrinhModel();
        diemThiModel = new DiemThiModel();
        getAllChuongTrinhFormMaSV();
        getAllKhoaHoc();
    }
    
    public void getAllChuongTrinhFormMaKhoaHoc(){
        try{
            // chuong trinh hoc 
            System.out.println("MaKhoaHoc: " + KhoaHoc);
            arrChuongTrinh = new ArrayList<ChuongTrinhEntity>();
            arrChuongTrinh = chuongTrinhModel.getChuongTrinhFormMaKhoaHoc(KhoaHoc);
            
            // lay ve danh sach hoc ky tu ma khoa hoc
            arrHocKy = new ArrayList<HocKyEntity>();
            arrHocKy = chuongTrinhModel.getAllHocKyFormMaKhoaHoc(KhoaHoc);

            // tao chuong trinh hoc cho tung hoc ky
            listHocKy = new ArrayList<HocKyEntity>();
            for (int i = 0; i < arrHocKy.size(); i++) {
                HocKyEntity hocKy = new HocKyEntity(arrHocKy.get(i).getTenHocKy());
                for (int j = 0; j < arrChuongTrinh.size(); j++) {
                    if (arrHocKy.get(i).getMaHocKy().equals(arrChuongTrinh.get(j).getMaHocKy())) {
                        String MaMon = arrChuongTrinh.get(j).getMaMon();
                        String TenMon = arrChuongTrinh.get(j).getTenMon();
                        int SoGio = arrChuongTrinh.get(j).getSoGio();
                        String GhiChu = arrChuongTrinh.get(j).getGhiChu();
                        hocKy.getListChuongTrinh().add(new ChuongTrinhEntity(MaMon, TenMon, SoGio, GhiChu));
                    }
                }
                listHocKy.add(hocKy);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public void getAllChuongTrinhFormMaSV() {
        try {
            HttpSession session = SessionBean.newSession(false);
            String MaSV = (String) session.getAttribute("username");

            // chuong trinh hoc 
            arrChuongTrinh = new ArrayList<ChuongTrinhEntity>();
            arrChuongTrinh = chuongTrinhModel.getChuongTrinhFormMaSV(MaSV);
            KhoaHoc = arrChuongTrinh.get(0).getMaKhoaHoc();
            // lay ve danh sach hoc ky tu ma sinh vien
            arrHocKy = new ArrayList<HocKyEntity>();
            arrHocKy = diemThiModel.getAllHocKy(MaSV);

            // tao chuong trinh hoc cho tung hoc ky
            listHocKy = new ArrayList<HocKyEntity>();
            for (int i = 0; i < arrHocKy.size(); i++) {
                HocKyEntity hocKy = new HocKyEntity(arrHocKy.get(i).getTenHocKy());
                for (int j = 0; j < arrChuongTrinh.size(); j++) {
                    if (arrHocKy.get(i).getMaHocKy().equals(arrChuongTrinh.get(j).getMaHocKy())) {
                        String MaMon = arrChuongTrinh.get(j).getMaMon();
                        String TenMon = arrChuongTrinh.get(j).getTenMon();
                        int SoGio = arrChuongTrinh.get(j).getSoGio();
                        String GhiChu = arrChuongTrinh.get(j).getGhiChu();
                        hocKy.getListChuongTrinh().add(new ChuongTrinhEntity(MaMon, TenMon, SoGio, GhiChu));
                    }
                }
                listHocKy.add(hocKy);
            }

        } catch (Exception ex) {
            System.out.println("" + ex);
        }
    }

    public void getAllKhoaHoc() {
        try {
            arrKhoaHoc = new ArrayList<KhoaHocEntity>();
            arrKhoaHoc = chuongTrinhModel.getAllKhoaHoc();

            KhoaHocList = new HashMap<String, String>();
            for (int i = 0; i < arrKhoaHoc.size(); i++) {
                KhoaHocList.put(arrKhoaHoc.get(i).getTenKhoaHoc(), arrKhoaHoc.get(i).getMaKhoaHoc());
                System.out.println("TenKhoaHoc: " + arrKhoaHoc.get(i).getTenKhoaHoc());
            }

        } catch (Exception ex) {

        }
    }

    public ChuongTrinhEntity getChuongTrinhEntity() {
        return chuongTrinhEntity;
    }

    public void setChuongTrinhEntity(ChuongTrinhEntity chuongTrinhEntity) {
        this.chuongTrinhEntity = chuongTrinhEntity;
    }

    public ChuongTrinhModel getChuongTrinhModel() {
        return chuongTrinhModel;
    }

    public void setChuongTrinhModel(ChuongTrinhModel chuongTrinhModel) {
        this.chuongTrinhModel = chuongTrinhModel;
    }

    public ArrayList<ChuongTrinhEntity> getArrChuongTrinh() {
        return arrChuongTrinh;
    }

    public void setArrChuongTrinh(ArrayList<ChuongTrinhEntity> arrChuongTrinh) {
        this.arrChuongTrinh = arrChuongTrinh;
    }

    public List<HocKyEntity> getListHocKy() {
        return listHocKy;
    }

    public void setListHocKy(List<HocKyEntity> listHocKy) {
        this.listHocKy = listHocKy;
    }

    public DiemThiModel getDiemThiModel() {
        return diemThiModel;
    }

    public void setDiemThiModel(DiemThiModel diemThiModel) {
        this.diemThiModel = diemThiModel;
    }

    public List<HocKyEntity> getArrHocKy() {
        return arrHocKy;
    }

    public void setArrHocKy(List<HocKyEntity> arrHocKy) {
        this.arrHocKy = arrHocKy;
    }

    public ArrayList<KhoaHocEntity> getArrKhoaHoc() {
        return arrKhoaHoc;
    }

    public void setArrKhoaHoc(ArrayList<KhoaHocEntity> arrKhoaHoc) {
        this.arrKhoaHoc = arrKhoaHoc;
    }

    public String getKhoaHoc() {
        return KhoaHoc;
    }

    public void setKhoaHoc(String KhoaHoc) {
        this.KhoaHoc = KhoaHoc;
    }

    public Map<String, String> getKhoaHocList() {
        return KhoaHocList;
    }

    public void setKhoaHocList(Map<String, String> KhoaHocList) {
        this.KhoaHocList = KhoaHocList;
    }

}
