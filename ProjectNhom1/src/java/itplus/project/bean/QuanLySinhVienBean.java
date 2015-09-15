/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.bean;

import itplus.project.entity.KhoaHocEntity;
import itplus.project.entity.LopHocEntity;
import itplus.project.entity.SinhVienEntity;
import itplus.project.model.LopHocModel;
import itplus.project.model.SinhVienModel;
import itplus.project.pool.DBPool;
import itplus.project.util.MessageUtil;
import itplus.project.util.ValidatorUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class QuanLySinhVienBean extends MessageUtil {

    private SinhVienEntity SinhVienEntity, rowSelected;
    private SinhVienModel SinhVienModel;
    private String focus = "txtMaSinhVien";
    private ArrayList<SinhVienEntity> arrSinhVien, listSinhVienSelected;

    private ArrayList<LopHocEntity> arrLopHoc;
    private String lopHoc;
    private Map<String, String> lopHocList = new HashMap<String, String>();
    private LopHocModel lopHocModel;
    private LopHocEntity lopHocEntity;
    private boolean checkUpdate;
    private boolean eventSelected = false;

    /**
     * Creates a new instance of SinhVienBean
     */
    public QuanLySinhVienBean() {
        SinhVienEntity = new SinhVienEntity();
        SinhVienModel = new SinhVienModel();
        lopHocEntity = new LopHocEntity();
        lopHocModel = new LopHocModel();
        getAllSinhVien();
        getAllLopHoc();
        checkStatusButton();
        checkSelectedTable();
    }

    public void getAllSinhVien() {
        try {
            arrSinhVien = SinhVienModel.getSinhVien();

        } catch (Exception ex) {
            System.out.println(ex.toString());
            Logger.getLogger(QuanLySinhVienBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getAllLopHoc() {
        try {
            arrLopHoc = new ArrayList<LopHocEntity>();
            arrLopHoc = lopHocModel.getAllLopHoc();
        } catch (Exception ex) {
            System.out.println("DungNT: " + ex.toString());
            Logger.getLogger(QuanLySinhVienBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        lopHocList = new HashMap<String, String>();
        for (int i = 0; i < arrLopHoc.size(); i++) {
            lopHocList.put(arrLopHoc.get(i).getTenLop(), arrLopHoc.get(i).getMaLop());
            System.out.println("TenLop: " + arrLopHoc.get(i).getTenLop());
        }
    }

    public void addSinhVien() {
        checkUpdate = false;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String ngaysinh = format.format(SinhVienEntity.getNgaySinh());
        System.out.println("Ngay sinh default: " + SinhVienEntity.getNgaySinh());
        System.out.println("Ngày Sinh format: " + ngaysinh);

        if (isValidate()) {
            try {
                int id;
                SinhVienEntity.setMaLop(lopHoc);
                System.out.println("Ma Lop: " + lopHoc);

                id = SinhVienModel.addSinhVien(SinhVienEntity);

                // lay ve thong tin ten khoa hoc va he dao tao tu ma khoa hoc
                ArrayList<LopHocEntity> arrInfoLopHoc = new ArrayList<LopHocEntity>();
                String TenLopHoc = lopHocModel.getTenLopOld(lopHoc);

                SinhVienEntity.setTenLop(TenLopHoc);
                SinhVienEntity.setNgaySinhView(ngaysinh);
                arrSinhVien.add(SinhVienEntity);

                // lay ve danh sach cac mon hoc cua lop hoc
                String MaSV = SinhVienEntity.getMaSinhVien();
                System.out.println("Mã Sinh vien: " + MaSV);
                ArrayList<String> arrMaMon = new ArrayList<String>();
                arrMaMon = SinhVienModel.getMaMonFormMaLop(lopHoc);
                for (int i = 0; i < arrMaMon.size(); i++) {
                    SinhVienModel.addDiemSinhVien(arrMaMon.get(i), MaSV);
                }

                addSuccessMessage("Thêm mới thành công");
                // khoi tao lai doi tuong xoa trang tren giao dien
                SinhVienEntity = new SinhVienEntity();
                //focus vao firstname de nguoi dung co the nhap tiep
                focus = "txtMaSinhVien";

            } catch (Exception ex) {
                if (ex.toString().equals("Violation of PRIMARY KEY constraint 'PK_LOPHOC'")) {
                    addErrorMessage("Mã Lớp học đã được tạo");
                } else {
                    addErrorMessage("Không tạo được lớp học");
                }
                System.out.println(ex.toString());
                ex.printStackTrace();
            }
        }
    }

    public void editSinhVien() {
        checkUpdate = true;
        //goi ham edit ben model
        if (isValidate()) {
            try {
                // lay ve maLop cu
                String MaLopCu = SinhVienModel.getOldMaLop(SinhVienEntity.getMaSinhVien());

                if (!MaLopCu.equals(lopHoc)) {
                    // neu ma lop thay doi
                    String MaKhoaHocCu = SinhVienModel.getMaKhoaHocFromMaLop(MaLopCu);
                    String MaKhoaHocMoi = SinhVienModel.getMaKhoaHocFromMaLop(lopHoc);
                    if (!MaKhoaHocCu.equals(MaKhoaHocMoi)) {
                        // neu khac ma Khoa hoc thi xoa masv tu bang diem cua ma lop cu
                        SinhVienModel.deleteDiemFromMaSV(SinhVienEntity.getMaSinhVien());
                        // tao maSv vao bang diem voi MaLop moi
                        String MaSV = SinhVienEntity.getMaSinhVien();
                        System.out.println("Mã Sinh vien: " + MaSV);
                        ArrayList<String> arrMaMon = new ArrayList<String>();
                        arrMaMon = SinhVienModel.getMaMonFormMaLop(lopHoc);
                        for (int i = 0; i < arrMaMon.size(); i++) {
                            SinhVienModel.addDiemSinhVien(arrMaMon.get(i), MaSV);
                        }
                    }
                }

                SinhVienEntity.setMaLop(lopHoc);
                System.out.println("MaLop: " + lopHoc);
                SinhVienModel.editSinhVien(SinhVienEntity);

                //cap nhat tren giao dien
                // lay ve thong tin ten khoa hoc va he dao tao tu ma khoa hoc
                ArrayList<LopHocEntity> arrInfoLopHoc = new ArrayList<LopHocEntity>();
                String TenLopHoc = lopHocModel.getTenLopOld(lopHoc);

                SinhVienEntity.setTenLop(TenLopHoc);

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                String ngaysinh = format.format(SinhVienEntity.getNgaySinh());
                System.out.println("Ngay sinh default: " + SinhVienEntity.getNgaySinh());
                System.out.println("Ngày Sinh format: " + ngaysinh);
                SinhVienEntity.setNgaySinhView(ngaysinh);

                for (int i = 0; i < arrSinhVien.size(); i++) {
                    if (arrSinhVien.get(i).getMaSinhVien().equals(SinhVienEntity.getMaSinhVien())) {
                        arrSinhVien.set(i, SinhVienEntity);
                    }
                    System.out.println("arr: " + arrSinhVien.get(i).getMaSinhVien());
                }

//                for (SinhVienEntity sinhvien : arrSinhVien) {
//                    if (sinhvien.getMaSinhVien() == SinhVienEntity.getMaSinhVien()) {
//                        arrSinhVien.set(arrSinhVien.indexOf(sinhvien), SinhVienEntity);
//                    }
//                }
                addSuccessMessage("Sửa thành công");
            } catch (SQLException ex) {
                System.out.println(ex.toString());
                addErrorMessage(ex);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyLopBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void deleteSinhVien() {
        try {

            // xoa sinh vien trong bang diem
            SinhVienModel.deleteDiemSinhVien(listSinhVienSelected);
            //goi ham xoa ben model
            System.out.println("Listselected: " + listSinhVienSelected);
            SinhVienModel.deleteSinhVien(listSinhVienSelected);
            //xoa tren giao dien
            for (SinhVienEntity sinhVien : listSinhVienSelected) {
                arrSinhVien.remove(sinhVien);
            }
//            checkStatusButton();//ham enable hoac disable button tren giao dien
            SinhVienEntity = new SinhVienEntity();
            focus = "txtMaSinhVien";
            addSuccessMessage("Xóa thành công");
        } catch (Exception ex) {
            addErrorMessage(ex);
        }
    }

    public boolean isValidate() {
        if (ValidatorUtil.isSpaceString(SinhVienEntity.getMaSinhVien())) {
            addErrorMessage("Bạn chưa nhập mã sinh viên");
            focus = "txtMaSinhVien";
            return false;
        } else if (!ValidatorUtil.isNotKyThuDacBiet(SinhVienEntity.getMaSinhVien())) {
            addErrorMessage("Mã sinh viên không được chưa ký tự đặc biết");
            focus = "txtMaSinhVien";
            return false;
        } else if (ValidatorUtil.isMinLenght(SinhVienEntity.getMaSinhVien(), 3)) {
            addErrorMessage("Mã sinh viên không được ít hơn 5 ký tự");
            focus = "txtMaSinhVien";
            return false;
        } else if (!ValidatorUtil.isNotKyThuDacBiet(SinhVienEntity.getMaSinhVien())) {
            addErrorMessage("Mã sinh viên không được chưa ký tự đặc biệt");
            focus = "txtMaSinhVien";
            return false;
        } else if (checkDuplicateMaSV()) {
            addErrorMessage("Mã sinh viên đã tồn tại");
            focus = "txtMaSinhVien";
            return false;
        } else if (ValidatorUtil.isSpaceString(SinhVienEntity.getTenSinhVien())) {
            addErrorMessage("Bạn chưa nhập tên sinh viên");
            focus = "txtTenSinhVien";
            return false;
        } else if (ValidatorUtil.isMinLenght(SinhVienEntity.getTenSinhVien(), 5)) {
            addErrorMessage("Họ Tên sinh viên không được ít hưn 5 ký tự");
            focus = "txtTenSinhVien";
            return false;
        } else if (ValidatorUtil.isSpaceString(SinhVienEntity.getSDT())) {
            addErrorMessage("Bạn chưa nhập số điện thoại");
            focus = "txtSDT";
            return false;
        } else if (ValidatorUtil.isMinLenght(SinhVienEntity.getSDT(), 8)) {
            addErrorMessage("Số điện thoại không được nhập ít hơn 8 ký tự");
            focus = "txtSDT";
            return false;
        } else if (!ValidatorUtil.isNumber(SinhVienEntity.getSDT())) {
            addErrorMessage("Số điện thoại chỉ được nhập số");
            focus = "txtSDT";
            return false;
        } else if (ValidatorUtil.isSpaceString(SinhVienEntity.getDiaChi())) {
            addErrorMessage("Bạn chưa nhập địa chỉ");
            focus = "txtDiaChi";
            return false;
        } else if (SinhVienEntity.getNgaySinh() == null) {
            System.out.println("Bạn chưa nhập ngày sinh");
            addErrorMessage("Bạn chưa nhập ngày sinh");
            focus = "txtNgaySinh";
            return false;
        } else if (ValidatorUtil.isSpaceString(SinhVienEntity.getQueQuan())) {
            addErrorMessage("Bạn chưa nhập quê quán");
            focus = "txtQueQuan";
            return false;
        } else if (ValidatorUtil.isSelector(lopHoc)) {
            addErrorMessage("Bạn chưa chọn lớp học");
            focus = "txtLopHoc";
            return false;
        } else if (ValidatorUtil.isSpaceString(SinhVienEntity.getEmail())) {
            addErrorMessage("Bạn chưa nhập Email");
            focus = "txtEmail";
            return false;
        } else if (!ValidatorUtil.isEmail(SinhVienEntity.getEmail())) {
            System.out.println("giá trị email: " + ValidatorUtil.isEmail(SinhVienEntity.getEmail()));
            addErrorMessage("Email nhập không đúng định dạng");
            focus = "txtEmail";
            return false;
        } else if (ValidatorUtil.isSpaceString(SinhVienEntity.getGioiTinh())) {
            addErrorMessage("Bạn chưa chọn giới tính");
            focus = "txtGioiTinh";
            return false;
        } else {
            return true;
        }
    }

    public boolean checkDuplicateMaSV() {
        if (!checkUpdate) {
            try {
                if (SinhVienModel.checkDuplicateMaSinhVien(SinhVienEntity.getMaSinhVien())) {
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

    public String checkStatusButton() {
        if (arrSinhVien.isEmpty()) {
            return "true";
        } else {
            return "false";
        }
    }

    public void clearText() {
        SinhVienEntity = new SinhVienEntity();
        eventSelected = false;
        focus = "txtMaSinhVien";
    }

    public void selectedRowtable(SelectEvent event) {
        eventSelected = true;
        rowSelected = (SinhVienEntity) event.getObject();
        try {
            SinhVienEntity = rowSelected.clone();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Malop " + SinhVienEntity.getMaLop() + " TenLop: " + SinhVienEntity.getTenLop());
    }

    public String checkSelectedTable() {
        if (eventSelected) {
            return "true";
        } else {
            return "false";
        }
    }

    // getter and setter
    public SinhVienEntity getSinhVienEntity() {
        return SinhVienEntity;
    }

    public void setSinhVienEntity(SinhVienEntity SinhVienEntity) {
        this.SinhVienEntity = SinhVienEntity;
    }

    public SinhVienModel getSinhVienModel() {
        return SinhVienModel;
    }

    public void setSinhVienModel(SinhVienModel SinhVienModel) {
        this.SinhVienModel = SinhVienModel;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public ArrayList<SinhVienEntity> getArrSinhVien() {
        return arrSinhVien;
    }

    public void setArrSinhVien(ArrayList<SinhVienEntity> arrSinhVien) {
        this.arrSinhVien = arrSinhVien;
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

    public SinhVienEntity getRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(SinhVienEntity rowSelected) {
        this.rowSelected = rowSelected;
    }

    public ArrayList<SinhVienEntity> getListSinhVienSelected() {
        return listSinhVienSelected;
    }

    public void setListSinhVienSelected(ArrayList<SinhVienEntity> listSinhVienSelected) {
        this.listSinhVienSelected = listSinhVienSelected;
    }

}
