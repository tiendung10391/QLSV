/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.bean;

import itplus.project.entity.AdminEntity;
import itplus.project.entity.LopHocEntity;
import itplus.project.entity.MonHocEntity;
import itplus.project.model.AdminModel;
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
public class QuanLyAdminBean extends MessageUtil {

    /**
     * Creates a new instance of QuanLyAdminBean
     */
    
    private ArrayList<AdminEntity> arrAdmin, listAdminSelected;
    private AdminEntity adminEntity, rowSelected;
    private AdminModel adminModel;
    private String focus = "txtTenNguoiDung";
    private boolean checkUpdate;
    private boolean eventSelected = false;
    
    public QuanLyAdminBean() {
        adminEntity = new AdminEntity();
        adminModel = new AdminModel();
        getAllAdmin();
    }
    
    public void getAllAdmin(){
        try {
            arrAdmin = new ArrayList<AdminEntity>();
            arrAdmin = adminModel.getAllAdmin();
        } catch (Exception ex) {
            Logger.getLogger(QuanLyAdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addAdmin() {
        checkUpdate = false;
        if (isValidate()) {
            try {
                int id;
                
                id = adminModel.addAdmin(adminEntity);
                
                arrAdmin.add(0,adminEntity);

                addSuccessMessage("Thêm mới thành công");
                // khoi tao lai doi tuong xoa trang tren giao dien
                adminEntity = new AdminEntity();
                //focus vao firstname de nguoi dung co the nhap tiep
                focus = "txtTenNguoiDung";

            } catch (Exception ex) {
                addErrorMessage("Không tạo được tài khoản");
                System.out.println(ex.toString());
                ex.printStackTrace();
            }
        }
    }
    
    public void editAdmin() {
        checkUpdate = true;
        if (isValidate()) {
            try {
                adminModel.editAdmin(adminEntity);
                //cap nhat tren giao dien

                for (int i = 0; i < arrAdmin.size(); i++) {
                    if (arrAdmin.get(i).getId() == adminEntity.getId()) {
                        arrAdmin.set(i, adminEntity);
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
            adminModel.deleteAdmin(listAdminSelected);
            //xoa tren giao dien
            for (AdminEntity admin : listAdminSelected) {
                arrAdmin.remove(admin);
            }
//            checkStatusButton();//ham enable hoac disable button tren giao dien
            adminEntity = new AdminEntity();
            focus = "txtMaMon";
            addSuccessMessage("Xóa thành công");
        } catch (Exception ex) {
            addErrorMessage(ex);
        }
    }
    
    public boolean isValidate() {
        if (ValidatorUtil.isSpaceString(adminEntity.getTenNguoiDung())) {
            addErrorMessage("Bạn chưa nhập tên người dùng");
            focus = "txtTenNguoiDung";
            return false;
        } else if (ValidatorUtil.isSpaceString(adminEntity.getDiaChi())) {
            addErrorMessage("Bạn chưa nhập địa chỉ");
            focus = "txtDiaChi";
            return false;
        } else if (ValidatorUtil.isSpaceString(adminEntity.getSDT())) {
            addErrorMessage("Bạn chưa nhập số điện thoại");
            focus = "txtSDT";
            return false;
        }else if(ValidatorUtil.isMinLenght(adminEntity.getSDT(), 8)){
            addErrorMessage("Số điện thoại phải nhập nhiều hơn 8 ký tự");
            focus = "txtSDT";
            return false;
        } else if (!ValidatorUtil.isNumber(adminEntity.getSDT())) {
            addErrorMessage("Số điện thoại chỉ được nhập số");
            focus = "txtSDT";
            return false;
        }else if (ValidatorUtil.isSpaceString(adminEntity.getEmail())) {
            addErrorMessage("Bạn chưa nhập Email");
            focus = "txtEmail";
            return false;
        } else if (!ValidatorUtil.isEmail(adminEntity.getEmail())) {
            System.out.println("giá trị email: " + ValidatorUtil.isEmail(adminEntity.getEmail()));
            addErrorMessage("Email nhập không đúng định dạng");
            focus = "txtEmail";
            return false;
        } else if (ValidatorUtil.isSpaceString(adminEntity.getTenDangNhap())) {
            addErrorMessage("Bạn chưa nhập tên đăng nhập");
            focus = "txtTenDangNhap";
            return false;
        } else if (ValidatorUtil.isSpaceString(adminEntity.getMatKhau())) {
            addErrorMessage("Bạn chưa nhập mật khẩu");
            focus = "txtMatKhau";
            return false;
        } else {
            return true;
        }
    }
    
    public String checkStatusButton() {
        if (arrAdmin.isEmpty()) {
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
    
//    
//    public void selectedRowtable(SelectEvent event) {
//        System.out.println("size: " + listAdminSelected.size());
//        eventSelected = true;
//        rowSelected = (AdminEntity) event.getObject();
//        try {
//            adminEntity = rowSelected.clone();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
    
    public void selectedRowtable(SelectEvent event) {
        eventSelected = true;
        System.out.println("size: " + listAdminSelected.size());
        rowSelected = (AdminEntity) event.getObject();
        try {
            adminEntity = rowSelected.clone();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("id: " + adminEntity.getId() + " tendangnhap: " + adminEntity.getTenDangNhap());
    }
    
    public void clearText() {
        adminEntity = new AdminEntity();
        eventSelected = false;
        focus = "txtTenNguoiDung";
    }

    public ArrayList<AdminEntity> getArrAdmin() {
        return arrAdmin;
    }

    public void setArrAdmin(ArrayList<AdminEntity> arrAdmin) {
        this.arrAdmin = arrAdmin;
    }

    public ArrayList<AdminEntity> getListAdminSelected() {
        return listAdminSelected;
    }

    public void setListAdminSelected(ArrayList<AdminEntity> listAdminSelected) {
        this.listAdminSelected = listAdminSelected;
    }

    public AdminEntity getAdminEntity() {
        return adminEntity;
    }

    public void setAdminEntity(AdminEntity adminEntity) {
        this.adminEntity = adminEntity;
    }

    public AdminEntity getRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(AdminEntity rowSelected) {
        this.rowSelected = rowSelected;
    }

    public AdminModel getAdminModel() {
        return adminModel;
    }

    public void setAdminModel(AdminModel adminModel) {
        this.adminModel = adminModel;
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
