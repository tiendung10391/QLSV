/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.bean;

import itplus.project.entity.DiemEntity;
import itplus.project.entity.KhoaHocEntity;
import itplus.project.entity.LopHocEntity;
import itplus.project.entity.MonHocEntity;
import itplus.project.model.DiemModel;
import itplus.project.model.LopHocModel;
import itplus.project.model.MonHocModel;
import itplus.project.util.MessageUtil;
import itplus.project.util.ValidatorUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Dung NT
 */
public class QuanLyDiemBean extends MessageUtil {

    private String focus = "txtLopHoc";

    private LopHocModel lopHocModel;
    private LopHocEntity lopHocEntity;
    private ArrayList<LopHocEntity> arrLopHoc;
    private String lopHoc;
    private Map<String, String> lopHocList = new HashMap<String, String>();

    private MonHocEntity monHocEntity;
    private MonHocModel monHocModel;
    private ArrayList<MonHocEntity> arrMonHoc;
    private String monHoc;
    private Map<String, String> monHocList = new HashMap<String, String>();

    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();

    private DiemEntity diemEntity, rowSelected;
    private DiemModel diemModel;
    private ArrayList<DiemEntity> arrDiem = new ArrayList<DiemEntity>();

    /**
     * Creates a new instance of QuanLyDiemBean
     */
    public QuanLyDiemBean() {
        lopHocModel = new LopHocModel();
        lopHocEntity = new LopHocEntity();
        monHocEntity = new MonHocEntity();
        monHocModel = new MonHocModel();
        diemEntity = new DiemEntity();
        diemModel = new DiemModel();
        getAllLopHoc();
    }

    public void pushDiemSinhVien() {
        if (isValidateTimKiem()) {
            try {
                arrDiem = diemModel.getAllDiemSinhVienFromMaLopAndMaMon(lopHoc, monHoc);
            } catch (Exception ex) {
                System.out.println(ex);
                Logger.getLogger(QuanLyDiemBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void onRowEdit(RowEditEvent event) {
        try {
            //        FacesMessage msg = new FacesMessage("Car Edited", ((Car) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
            // lay ve doi tuong tren dong
            rowSelected = (DiemEntity) event.getObject();
            diemEntity = rowSelected.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(QuanLyDiemBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("MaSV: " + diemEntity.getMaSinhVien());
        String maSV = diemEntity.getMaSinhVien();

        if (isValidateEditDiem()) {
            try {
                diemModel.editDiem(diemEntity, monHoc, maSV);
            } catch (SQLException ex) {
                System.out.println(ex);
                Logger.getLogger(QuanLyDiemBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void onRowCancel(RowEditEvent event) {
        System.out.println("cancel edit");
//        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Car) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public boolean isValidateEditDiem() {
        if (!ValidatorUtil.isNumber(String.valueOf(diemEntity.getDiemLan1()))) {
            addErrorMessage("Điêm chỉ được nhập số");
            return false;
        } else if (!ValidatorUtil.isNumber(String.valueOf(diemEntity.getDiemLan2()))) {
            addErrorMessage("Điểm chỉ được nhập số");
            return false;
        } else if (!ValidatorUtil.isNumber(String.valueOf(diemEntity.getDiemLan3()))) {
            addErrorMessage("Điểm chỉ được nhập số");
            return false;
        } else {
            return true;
        }
    }

    public boolean isValidateTimKiem() {
        if (ValidatorUtil.isSelector(lopHoc)) {
            addErrorMessage("Bạn chưa chọn lớp học");
            return false;
        } else if (ValidatorUtil.isSelector(monHoc)) {
            addErrorMessage("Bạn chưa chọn môn học");
            return false;
        } else {
            return true;
        }
    }

    public void getAllLopHoc() {
        try {
            arrLopHoc = new ArrayList<LopHocEntity>();
            arrLopHoc = lopHocModel.getAllLopHoc();
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(QuanLyDiemBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        lopHocList = new HashMap<String, String>();
        for (int i = 0; i < arrLopHoc.size(); i++) {
            lopHocList.put(arrLopHoc.get(i).getTenLop(), arrLopHoc.get(i).getMaLop());
        }

    }

    public Map<String, Map<String, String>> getDataMonHoc() {
        return data;
    }

    public void onKhoaHocChange() {
        ArrayList<MonHocEntity> monHocSelected = new ArrayList<MonHocEntity>();
        try {
            monHocSelected = monHocModel.getAllMonHocFormMaLop(lopHoc);
            Map<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < monHocSelected.size(); i++) {
                map.put(monHocSelected.get(i).getTenMonHoc(), monHocSelected.get(i).getMaMonHoc());
            }
            data.put(lopHoc, map);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            Logger.getLogger(QuanLyLopBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (lopHoc != null && !lopHoc.equals("")) {
            monHocList = data.get(lopHoc);
        } else {
            monHocList = new HashMap<String, String>();
        }
    }

    public DiemEntity getDiemEntity() {
        return diemEntity;
    }

    public void setDiemEntity(DiemEntity diemEntity) {
        this.diemEntity = diemEntity;
    }

    public DiemModel getDiemModel() {
        return diemModel;
    }

    public void setDiemModel(DiemModel diemModel) {
        this.diemModel = diemModel;
    }

    public ArrayList<DiemEntity> getArrDiem() {
        return arrDiem;
    }

    public void setArrDiem(ArrayList<DiemEntity> arrDiem) {
        this.arrDiem = arrDiem;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public LopHocModel getLopHocModel() {
        return lopHocModel;
    }

    public void setLopHocModel(LopHocModel lopHocModel) {
        this.lopHocModel = lopHocModel;
    }

    public LopHocEntity getLopHocEntity() {
        return lopHocEntity;
    }

    public void setLopHocEntity(LopHocEntity lopHocEntity) {
        this.lopHocEntity = lopHocEntity;
    }

    public ArrayList<LopHocEntity> getArrLopHoc() {
        return arrLopHoc;
    }

    public void setArrLopHoc(ArrayList<LopHocEntity> arrLopHoc) {
        this.arrLopHoc = arrLopHoc;
    }

    public String getLopHoc() {
        return lopHoc;
    }

    public void setLopHoc(String lopHoc) {
        this.lopHoc = lopHoc;
    }

    public Map<String, String> getLopHocList() {
        return lopHocList;
    }

    public void setLopHocList(Map<String, String> lopHocList) {
        this.lopHocList = lopHocList;
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
        return monHocList;
    }

    public void setMonHocList(Map<String, String> monHocList) {
        this.monHocList = monHocList;
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

}
