/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.bean;

import itplus.project.entity.LopHocEntity;
import itplus.project.entity.MonHocEntity;
import itplus.project.entity.SinhVienEntity;
import itplus.project.model.MonHocModel;
import itplus.project.util.MessageUtil;
import itplus.project.util.ValidatorUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Dung NT
 */
public class QuanLyMonHocBean extends MessageUtil {

    private MonHocEntity monHocEntity, rowSelected;
    private MonHocModel monHocModel;
    private ArrayList<MonHocEntity> arrMonHoc, listLopHocSelected;
    private String focus = "txtMaMon";
    private boolean checkUpdate;
    private boolean eventSelected = false;

    /**
     * Creates a new instance of QuanLyMonHocBean
     */
    public QuanLyMonHocBean() {
        monHocEntity = new MonHocEntity();
        monHocModel = new MonHocModel();
        getAllMonHoc();
        checkStatusButton();
    }

    public void addMonHoc() {
        checkUpdate = false;
        if (isValidate()) {
            try {
                int id;

                id = monHocModel.addMonHoc(monHocEntity);
                arrMonHoc.add(0,monHocEntity);

                addSuccessMessage("Thêm mới thành công");
                // khoi tao lai doi tuong xoa trang tren giao dien
                monHocEntity = new MonHocEntity();
                //focus vao firstname de nguoi dung co the nhap tiep
                focus = "txtMaMon";

            } catch (Exception ex) {
                addErrorMessage("Không tạo được môn học");
                System.out.println(ex.toString());
                ex.printStackTrace();
            }
        }
    }

    public void editMonHoc() {
        checkUpdate = true;
        if (isValidate()) {
            try {
                monHocModel.editMonHoc(monHocEntity);
                //cap nhat tren giao dien

                for (int i = 0; i < arrMonHoc.size(); i++) {
                    if (arrMonHoc.get(i).getMaMonHoc().equals(monHocEntity.getMaMonHoc())) {
                        arrMonHoc.set(i, monHocEntity);
                    }
                }

                addSuccessMessage("Sửa thành công");
            } catch (SQLException ex) {
                addErrorMessage("Lỗi không sửa được");
            } catch (Exception ex) {
                Logger.getLogger(QuanLyLopBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteMonHoc() {
        try {
            //goi ham xoa ben model
            monHocModel.deleteMonHoc(listLopHocSelected);
            //xoa tren giao dien
            for (MonHocEntity monHoc : listLopHocSelected) {
                arrMonHoc.remove(monHoc);
            }
//            checkStatusButton();//ham enable hoac disable button tren giao dien
            monHocEntity = new MonHocEntity();
            focus = "txtMaMon";
            addSuccessMessage("Xóa thành công");
        } catch (Exception ex) {
            addErrorMessage(ex);
        }
    }

    public boolean isValidate() {
        if (ValidatorUtil.isSpaceString(monHocEntity.getMaMonHoc())) {
            addErrorMessage("Bạn chưa nhập mã môn học");
            focus = "txtMaMon";
            return false;
        } else if (!ValidatorUtil.isNotKyThuDacBiet(monHocEntity.getMaMonHoc())) {
            addErrorMessage("Mã môn học không được chưa ký tự đặc biệt");
            focus = "txtMaMon";
            return false;
        }else if(checkDuplicateMaSV()){
            addErrorMessage("Mã môn học đã tồn tại");
            focus = "txtMaMon";
            return false;
        }else if (ValidatorUtil.isSpaceString(monHocEntity.getTenMonHoc())) {
            addErrorMessage("Bạn chưa nhập tên môn học");
            focus = "txtTenMon";
            return false;
        } else if (ValidatorUtil.isSpaceString(monHocEntity.getSoGio())) {
            addErrorMessage("Bạn chưa nhập số giờ");
            focus = "txtSoGio";
            return false;
        } else if (!ValidatorUtil.isNumber(monHocEntity.getSoGio())) {
            addErrorMessage("Số giờ chỉ được nhập số");
            focus = "txtSoGio";
            return false;

        }else {
            return true;
        }
    }

    public String checkStatusButton() {
        if (arrMonHoc.isEmpty()) {
            return "true";
        } else {
            return "false";
        }
    }
    
    public String checkSelectedTable(){
        if(eventSelected){
            return "true";
        }else {
            return "false";
        }
    }

    public void selectedRowtable(SelectEvent event) {
        eventSelected = true;
        rowSelected = (MonHocEntity) event.getObject();
        try {
            monHocEntity = rowSelected.clone();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearText() {
        monHocEntity = new MonHocEntity();
        eventSelected = false;
        focus = "txtMaMon";
    }

    public boolean checkDuplicateMaSV() {
        if (!checkUpdate) {
            try {
                if (monHocModel.checkDuplicateMaMonHoc(monHocEntity.getMaMonHoc())) {
                    return true;
                } else {
                    return false;
                }

            } catch (Exception ex) {
                Logger.getLogger(QuanLySinhVienBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    public void getAllMonHoc() {
        try {
            arrMonHoc = monHocModel.getAllMonHoc();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            Logger.getLogger(QuanLyMonHocBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // getter and setter
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

    public ArrayList<MonHocEntity> getListLopHocSelected() {
        return listLopHocSelected;
    }

    public void setListLopHocSelected(ArrayList<MonHocEntity> listLopHocSelected) {
        this.listLopHocSelected = listLopHocSelected;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public boolean isCheckUpdate() {
        return checkUpdate;
    }

    public void setCheckUpdate(boolean checkUpdate) {
        this.checkUpdate = checkUpdate;
    }

    public boolean isEventSelected() {
        return eventSelected;
    }

    public void setEventSelected(boolean eventSelected) {
        this.eventSelected = eventSelected;
    }

}
