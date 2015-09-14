/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.bean;

import itplus.project.entity.HocKyEntity;
import itplus.project.entity.KhoaHocEntity;
import itplus.project.entity.KhoaHocMonHocEntity;
import itplus.project.entity.LopHocEntity;
import itplus.project.entity.MonHocEntity;
import itplus.project.entity.SinhVienEntity;
import itplus.project.model.HocKyModel;
import itplus.project.model.KhoaHocModel;
import itplus.project.model.KhoaHocMonHocModel;
import itplus.project.model.MonHocModel;
import itplus.project.util.MessageUtil;
import itplus.project.util.ValidatorUtil;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Dung NT
 */
public class QuanLyKhoaHocMonHocBean extends MessageUtil {

    // tao list khoa hoc
    private KhoaHocEntity khoaHocEntity;
    private KhoaHocModel khoaHocModel;
    private ArrayList<KhoaHocEntity> arrKhoaHoc;
    private String khoaHoc;
    private Map<String, String> KhoaHocList = new HashMap<String, String>();

    // tao list hoc ky
    private HocKyEntity hocKyEntity;
    private HocKyModel hocKyModel;
    private ArrayList<HocKyEntity> arrHocKy;
    private String hocKy;
    private Map<String, String> hocKyList = new HashMap<String, String>();

    // tao list mon hoc
    private MonHocEntity monHocEntity;
    private MonHocModel monHocModel;
    private ArrayList<MonHocEntity> arrMonHoc;
    private String monHoc;
    private Map<String, String> MonHocList = new HashMap<String, String>();

    //quan ly khoa hoc mon hoc bean
    private String focus = "txtKhoaHoc";
    private KhoaHocMonHocEntity khoaHocMonHocEntity, rowSelected;
    private KhoaHocMonHocModel khoaHocMonHocModel;
    private ArrayList<KhoaHocMonHocEntity> arrKhoaHocMonHoc, listKhoaHocMonHocSelected;

    /**
     * Creates a new instance of QuanLyKhoaHocMonHocBean
     */
    public QuanLyKhoaHocMonHocBean() {
        // khoi tao khoa hoc
        khoaHocEntity = new KhoaHocEntity();
        khoaHocModel = new KhoaHocModel();
        // khoi tao hoc ky
        hocKyEntity = new HocKyEntity();
        hocKyModel = new HocKyModel();
        // khoi tao mon hoc
        monHocEntity = new MonHocEntity();
        monHocModel = new MonHocModel();
        // khoi tao khoahocmonhoc
        khoaHocMonHocEntity = new KhoaHocMonHocEntity();
        khoaHocMonHocModel = new KhoaHocMonHocModel();

        getAllKhoaHoc();
        getAllHocKy();
        getAllMonHoc();
        getAllKhoaHocMonHoc();
    }

    //quan ly khoa hoc mon hoc
    public void getAllKhoaHocMonHoc() {
        try {
            arrKhoaHocMonHoc = new ArrayList<KhoaHocMonHocEntity>();
            arrKhoaHocMonHoc = khoaHocMonHocModel.getAllKhoaHocMonHoc();
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(QuanLyKhoaHocMonHocBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addKhoaHoc() {

        if (isValidate()) {
            try {
                int id;
                khoaHocMonHocEntity.setMaHocKy(hocKy);
                khoaHocMonHocEntity.setMaKhoaHoc(khoaHoc);
                khoaHocMonHocEntity.setMaMonHoc(monHoc);

                id = khoaHocMonHocModel.addKhoaHocMonHoc(khoaHocMonHocEntity);

                arrKhoaHocMonHoc.add(khoaHocMonHocEntity);
                String tenKhoaHoc = khoaHocModel.getTenKhoaHoc(khoaHoc);
                String tenHocKy = hocKyModel.getTenHocKy(hocKy);
                String tenMonHoc = monHocModel.getTenMonHoc(monHoc);

                khoaHocMonHocEntity.setTenKhoaHoc(tenKhoaHoc);
                khoaHocMonHocEntity.setTenHocKy(tenHocKy);
                khoaHocMonHocEntity.setTenMonHoc(tenMonHoc);

                addSuccessMessage("Thêm mới thành công");
                // khoi tao lai doi tuong xoa trang tren giao dien
                khoaHocMonHocEntity = new KhoaHocMonHocEntity();
                //focus vao firstname de nguoi dung co the nhap tiep

            } catch (Exception ex) {
                addErrorMessage("Không tạo được khóa học");
                System.out.println(ex.toString());
                ex.printStackTrace();
            }
        }
    }

    public void editKhoaHoc() {
        if (isValidate()) {
            try {
                System.out.println(khoaHocMonHocEntity.getIdKhoaHocMonHoc() + ": id khoahoc");
                khoaHocMonHocEntity.setMaHocKy(hocKy);
                khoaHocMonHocEntity.setMaKhoaHoc(khoaHoc);
                khoaHocMonHocEntity.setMaMonHoc(monHoc);

                khoaHocMonHocModel.editKhoaHocMonHoc(khoaHocMonHocEntity);
                //cap nhat tren giao dien

                // lay ve thong tin ten khoa hoc va he dao tao tu ma khoa hoc
                String tenKhoaHoc = khoaHocModel.getTenKhoaHoc(khoaHoc);
                String tenHocKy = hocKyModel.getTenHocKy(hocKy);
                String tenMonHoc = monHocModel.getTenMonHoc(monHoc);

                khoaHocMonHocEntity.setTenKhoaHoc(tenKhoaHoc);
                khoaHocMonHocEntity.setTenHocKy(tenHocKy);
                khoaHocMonHocEntity.setTenMonHoc(tenMonHoc);

//                for (int i = 0; i < arrSinhVien.size(); i++) {
//                    if (arrSinhVien.get(i).getMaSinhVien().equals(SinhVienEntity.getMaSinhVien())) {
//                        arrSinhVien.set(i, SinhVienEntity);
//                    }
//                    System.out.println("arr: " + arrSinhVien.get(i).getMaSinhVien());
//                }

                for (KhoaHocMonHocEntity khoaHoc : arrKhoaHocMonHoc) {
                    if (khoaHoc.getIdKhoaHocMonHoc() == khoaHocMonHocEntity.getIdKhoaHocMonHoc()) {
                        arrKhoaHocMonHoc.set(arrKhoaHocMonHoc.indexOf(khoaHoc), khoaHocMonHocEntity);
                    }
                }
                addSuccessMessage("Sửa thành công");
            } catch (SQLException ex) {
                System.out.println(ex.toString());
                addErrorMessage(ex);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyLopBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deleteKhoaHoc(){
         try {
            //goi ham xoa ben model
            khoaHocMonHocModel.deleteKhoaHocMonHoc(listKhoaHocMonHocSelected);
            //xoa tren giao dien
            for (KhoaHocMonHocEntity khoaHoc : listKhoaHocMonHocSelected) {
                arrKhoaHocMonHoc.remove(khoaHoc);
            }
//            checkStatusButton();//ham enable hoac disable button tren giao dien
            khoaHocMonHocEntity = new KhoaHocMonHocEntity();
            addSuccessMessage("Xóa thành công");
        } catch (Exception ex) {
            addErrorMessage(ex);
        }
    }

    public boolean isValidate() {
        if (ValidatorUtil.isSelector(khoaHoc)) {
            addErrorMessage("Bạn chưa chọn khóa học");
            focus = "txtKhoaHoc";
            return false;
        } else if (ValidatorUtil.isSelector(hocKy)) {
            addErrorMessage("Bạn chưa chọn học kỳ");
            focus = "txtHocKy";
            return false;
        } else if (ValidatorUtil.isSelector(monHoc)) {
            addErrorMessage("Bạn chưa chọn môn học");
            focus = "txtMonHoc";
            return false;
        } else if (!ValidatorUtil.isNumber(String.valueOf(khoaHocMonHocEntity.getThuTu()))) {
            addErrorMessage("Thứ tự chỉ được nhập số");
            focus = "txtThuTu";
            return false;
        } else if (ValidatorUtil.isChuaNhapInt(khoaHocMonHocEntity.getThuTu())) {
            addErrorMessage("bạn chưa nhập thứ tự");
            focus = "txtThuTu";
            return false;
        } else {
            return true;
        }
    }

    // check khoa hoc mon hoc
    public String checkStatusButton() {

        if (arrKhoaHocMonHoc.isEmpty()) {
            return "true";
        } else {
            return "false";
        }
    }

    public void clearText() {
        khoaHocMonHocEntity = new KhoaHocMonHocEntity();
        focus = "txtKhoaHoc";
    }

    public void selectedRowtable(SelectEvent event) {
        rowSelected = (KhoaHocMonHocEntity) event.getObject();
        try {
            khoaHocMonHocEntity = rowSelected.clone();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // lay ve list cac mon hoc
    public void getAllMonHoc() {
        try {
            arrMonHoc = new ArrayList<MonHocEntity>();
            arrMonHoc = monHocModel.getAllMonHoc();
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(QuanLyKhoaHocMonHocBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        MonHocList = new HashMap<String, String>();
        for (int i = 0; i < arrMonHoc.size(); i++) {
            MonHocList.put(arrMonHoc.get(i).getTenMonHoc(), arrMonHoc.get(i).getMaMonHoc());
            System.out.println("TenMonHoc: " + arrMonHoc.get(i).getTenMonHoc());
        }

    }

    // lay ve list hoc ky
    public void getAllHocKy() {

        try {
            arrHocKy = new ArrayList<HocKyEntity>();
            arrHocKy = hocKyModel.getAllHocKy();
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(QuanLyKhoaHocMonHocBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        hocKyList = new HashMap<String, String>();
        for (int i = 0; i < arrHocKy.size(); i++) {
            hocKyList.put(arrHocKy.get(i).getTenHocKy(), arrHocKy.get(i).getMaHocKy());
            System.out.println("TenHocKy: " + arrHocKy.get(i).getTenHocKy());
        }
    }

    // lay ve list khoa hoc
    public void getAllKhoaHoc() {
        try {
            arrKhoaHoc = new ArrayList<KhoaHocEntity>();
            arrKhoaHoc = khoaHocModel.getAllKhoaHoc();
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(QuanLyKhoaHocMonHocBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        KhoaHocList = new HashMap<String, String>();
        for (int i = 0; i < arrKhoaHoc.size(); i++) {
            KhoaHocList.put(arrKhoaHoc.get(i).getTenKhoaHoc(), arrKhoaHoc.get(i).getMaKhoaHoc());
            System.out.println("TenKhoaHoc: " + arrKhoaHoc.get(i).getTenKhoaHoc());
        }
    }

    // getter and setter
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
        return KhoaHocList;
    }

    public void setKhoaHocList(Map<String, String> KhoaHocList) {
        this.KhoaHocList = KhoaHocList;
    }

    public HocKyEntity getHocKyEntity() {
        return hocKyEntity;
    }

    public void setHocKyEntity(HocKyEntity hocKyEntity) {
        this.hocKyEntity = hocKyEntity;
    }

    public HocKyModel getHocKyModel() {
        return hocKyModel;
    }

    public void setHocKyModel(HocKyModel hocKyModel) {
        this.hocKyModel = hocKyModel;
    }

    public ArrayList<HocKyEntity> getArrHocKy() {
        return arrHocKy;
    }

    public void setArrHocKy(ArrayList<HocKyEntity> arrHocKy) {
        this.arrHocKy = arrHocKy;
    }

    public String getHocKy() {
        return hocKy;
    }

    public void setHocKy(String hocKy) {
        this.hocKy = hocKy;
    }

    public Map<String, String> getHocKyList() {
        return hocKyList;
    }

    public void setHocKyList(Map<String, String> hocKyList) {
        this.hocKyList = hocKyList;
    }

    public MonHocEntity getMonHocEntity() {
        return monHocEntity;
    }

    public void setMonHocEntity(MonHocEntity monHocEntity) {
        this.monHocEntity = monHocEntity;
    }

    public MonHocModel getMonHocModel() {
        return monHocModel;
    }

    public void setMonHocModel(MonHocModel monHocModel) {
        this.monHocModel = monHocModel;
    }

    public ArrayList<MonHocEntity> getArrMonHoc() {
        return arrMonHoc;
    }

    public void setArrMonHoc(ArrayList<MonHocEntity> arrMonHoc) {
        this.arrMonHoc = arrMonHoc;
    }

    public String getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(String monHoc) {
        this.monHoc = monHoc;
    }

    public Map<String, String> getMonHocList() {
        return MonHocList;
    }

    public void setMonHocList(Map<String, String> MonHocList) {
        this.MonHocList = MonHocList;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public KhoaHocMonHocEntity getKhoaHocMonHocEntity() {
        return khoaHocMonHocEntity;
    }

    public void setKhoaHocMonHocEntity(KhoaHocMonHocEntity khoaHocMonHocEntity) {
        this.khoaHocMonHocEntity = khoaHocMonHocEntity;
    }

    public KhoaHocMonHocEntity getRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(KhoaHocMonHocEntity rowSelected) {
        this.rowSelected = rowSelected;
    }

    public KhoaHocMonHocModel getKhoaHocMonHocModel() {
        return khoaHocMonHocModel;
    }

    public void setKhoaHocMonHocModel(KhoaHocMonHocModel khoaHocMonHocModel) {
        this.khoaHocMonHocModel = khoaHocMonHocModel;
    }

    public ArrayList<KhoaHocMonHocEntity> getArrKhoaHocMonHoc() {
        return arrKhoaHocMonHoc;
    }

    public void setArrKhoaHocMonHoc(ArrayList<KhoaHocMonHocEntity> arrKhoaHocMonHoc) {
        this.arrKhoaHocMonHoc = arrKhoaHocMonHoc;
    }

    public ArrayList<KhoaHocMonHocEntity> getListKhoaHocMonHocSelected() {
        return listKhoaHocMonHocSelected;
    }

    public void setListKhoaHocMonHocSelected(ArrayList<KhoaHocMonHocEntity> listKhoaHocMonHocSelected) {
        this.listKhoaHocMonHocSelected = listKhoaHocMonHocSelected;
    }

}
