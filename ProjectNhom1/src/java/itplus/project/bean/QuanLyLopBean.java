/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.bean;

import itplus.project.entity.KhoaHocEntity;
import itplus.project.entity.LopHocEntity;
import itplus.project.entity.NganhEntity;
import itplus.project.model.KhoaHocModel;
import itplus.project.model.LopHocModel;
import itplus.project.model.NganhModel;
import itplus.project.util.MessageUtil;
import itplus.project.util.ValidatorUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dung NT
 */
public class QuanLyLopBean extends MessageUtil {

    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
    private NganhEntity nganhEntity;
    private String focus = "txtMaLop";
    private NganhModel nganhModel;
    private ArrayList<NganhEntity> arrNganh;
    private String nganh;
    private Map<String, String> nganhList = new HashMap<String, String>();
    private KhoaHocEntity khoaHocEntity;
    private KhoaHocModel khoaHocModel;
    private ArrayList<KhoaHocEntity> arrKhoaHoc;
    private String khoaHoc;
    private Map<String, String> khoaHocList = new HashMap<String, String>();
    private LopHocEntity lopHocEntity;
    private LopHocModel lopHocModel;
    private ArrayList<LopHocEntity> arrLopHoc;

    /**
     * Creates a new instance of QuanLyLopBean
     */
    public QuanLyLopBean() {
        nganhEntity = new NganhEntity();
        nganhModel = new NganhModel();
        khoaHocEntity = new KhoaHocEntity();
        khoaHocModel = new KhoaHocModel();
        lopHocEntity = new LopHocEntity();
        lopHocModel = new LopHocModel();
        lopHocEntity = new LopHocEntity();
        lopHocModel = new LopHocModel();
        getAllLopHoc();
        getAllNganh();
        setKhoaHoc();
        checkStatusButton();
    }

    public void getAllLopHoc() {
        try {
            arrLopHoc = lopHocModel.getLophoc();
            System.out.println("list Lop Hoc: " + arrLopHoc);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            Logger.getLogger(QuanLyLopBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addLop() {
        if (isValidate()) {
            try {
                int id;
                lopHocEntity.setMaKhoaHoc(khoaHoc);
                System.out.println("Lop hoc entity: " + lopHocEntity.getMaLop() + " " + lopHocEntity.getMaKhoaHoc());
                id = lopHocModel.addLophoc(lopHocEntity);
                //add to arraylist
                arrLopHoc.add(lopHocEntity);
                addSuccessMessage("Thêm mới thành công");
                lopHocEntity = new LopHocEntity();
                //focus vao firstname de nguoi dung co the nhap tiep
                focus = "txtMaLop";
//                checkStatusButton();
            } catch (Exception ex) {
                addErrorMessage("Không tạo được lớp học");
                ex.printStackTrace();
            }
        }
    }

    public boolean isValidate() {
        if (ValidatorUtil.isSpaceString(lopHocEntity.getMaLop())) {
            addErrorMessage("Chưa nhập mã lớp học");
            focus = "txtMaLop";
            return false;
        } else if (!ValidatorUtil.isNotKyThuDacBiet(lopHocEntity.getMaLop())) {
            addErrorMessage("Mã lớp không được chưa ký tự đặc biệt");
            focus = "txtMaLop";
            return false;
        } else if (ValidatorUtil.isSpaceString(lopHocEntity.getTenLop())) {
            addErrorMessage("Chưa nhập tên lớp học");
            focus = "txtTenLop";
            return false;
        } else if (!ValidatorUtil.isNotKyThuDacBiet(lopHocEntity.getTenLop())) {
            addErrorMessage("Tên Lớp không được chưa ký tự đặc biệt");
            focus = "txtTenLop";
            return false;
        } else if (ValidatorUtil.isSpaceString(lopHocEntity.getNamNhapHoc())) {
            addErrorMessage("Chưa nhập năm nhập học");
            focus = "txtNamNhapHoc";
            return false;
        } else if (ValidatorUtil.isSelector(nganh)) {
            System.out.println("Ngành:" + nganh);
            addErrorMessage("chưa chọn ngành học");
            return false;
        } else if (ValidatorUtil.isSelector(khoaHoc)) {
            addErrorMessage("chưa chọn khóa học");
            return false;
        } else {
            return true;
        }
    }

    public void getAllNganh() {
        try {
            arrNganh = nganhModel.getAllNganh();
            System.out.println("list nganh: " + arrNganh.toString());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        nganhList = new HashMap<String, String>();
        for (int i = 0; i < arrNganh.size(); i++) {
            nganhList.put(arrNganh.get(i).getTenNganh(), arrNganh.get(i).getMaNganh());
        }
    }

    public void getAllKhoaHoc() {
        try {
            arrKhoaHoc = khoaHocModel.getAllKhoaHoc();

            System.out.println("Ma nganh: " + nganh);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        khoaHocList = new HashMap<String, String>();
        for (int i = 0; i < arrKhoaHoc.size(); i++) {
            khoaHocList.put(arrKhoaHoc.get(i).getTenKhoaHoc(), arrKhoaHoc.get(i).getMaKhoaHoc());

        }
    }

    public ArrayList<KhoaHocEntity> getKhoaHocFormMaNganh() {
        try {
            arrKhoaHoc = khoaHocModel.getKhoaHocFormMaNganh(nganh);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return arrKhoaHoc;
    }

    public void setKhoaHoc() {
        // lay ve khoa hoc nganh lap trinh
        ArrayList<KhoaHocEntity> khocHocLT = new ArrayList<KhoaHocEntity>();
        try {
            khocHocLT = khoaHocModel.getKhoaHocFormMaNganh("LT");
            Map<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < khocHocLT.size(); i++) {
                map.put(khocHocLT.get(i).getTenKhoaHoc(), khocHocLT.get(i).getMaKhoaHoc());
            }
            data.put("LT", map);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            Logger.getLogger(QuanLyLopBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        // lay ve khoa hoc nganh do hoa
        ArrayList<KhoaHocEntity> khocHocDH = new ArrayList<KhoaHocEntity>();
        try {
            khocHocLT = khoaHocModel.getKhoaHocFormMaNganh("DH");
            Map<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < khocHocLT.size(); i++) {
                map.put(khocHocLT.get(i).getTenKhoaHoc(), khocHocLT.get(i).getMaKhoaHoc());
            }
            data.put("DH", map);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            Logger.getLogger(QuanLyLopBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String checkStatusButton() {
        if (arrLopHoc.isEmpty()) {
            return "true";
        } else {
            return "false";
        }
    }

    public Map<String, Map<String, String>> getDataKhoaHoc() {
        return data;
    }

    public void onKhoaHocChange() {
        if (nganh != null && !nganh.equals("")) {
            khoaHocList = data.get(nganh);
        } else {
            khoaHocList = new HashMap<String, String>();
        }
    }

    public NganhEntity getNganhEntity() {
        return nganhEntity;
    }

    public void setNganhEntity(NganhEntity nganhEntity) {
        this.nganhEntity = nganhEntity;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public NganhModel getNganhModel() {
        return nganhModel;
    }

    public void setNganhModel(NganhModel nganhModel) {
        this.nganhModel = nganhModel;
    }

    public ArrayList<NganhEntity> getArrNganh() {
        return arrNganh;
    }

    public void setArrNganh(ArrayList<NganhEntity> arrNganh) {
        this.arrNganh = arrNganh;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }

    public Map<String, String> getNganhList() {
        return nganhList;
    }

    public void setNganhList(Map<String, String> nganhList) {
        this.nganhList = nganhList;
    }

    public KhoaHocEntity getKhoaHocEntity() {
        return khoaHocEntity;
    }

    public void setKhoaHocEntity(KhoaHocEntity khoaHocEntity) {
        this.khoaHocEntity = khoaHocEntity;
    }

    public KhoaHocModel getKhoaHocModel() {
        return khoaHocModel;
    }

    public void setKhoaHocModel(KhoaHocModel khoaHocModel) {
        this.khoaHocModel = khoaHocModel;
    }

    public ArrayList<KhoaHocEntity> getArrKhoaHoc() {
        return arrKhoaHoc;
    }

    public void setArrKhoaHoc(ArrayList<KhoaHocEntity> arrKhoaHoc) {
        this.arrKhoaHoc = arrKhoaHoc;
    }

    public String getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(String khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public Map<String, String> getKhoaHocList() {
        return khoaHocList;
    }

    public void setKhoaHocList(Map<String, String> khoaHocList) {
        this.khoaHocList = khoaHocList;
    }

    public LopHocEntity getLopHocEntity() {
        return lopHocEntity;
    }

    public void setLopHocEntity(LopHocEntity lopHocEntity) {
        this.lopHocEntity = lopHocEntity;
    }

    public LopHocModel getLopHocModel() {
        return lopHocModel;
    }

    public void setLopHocModel(LopHocModel lopHocModel) {
        this.lopHocModel = lopHocModel;
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

}
