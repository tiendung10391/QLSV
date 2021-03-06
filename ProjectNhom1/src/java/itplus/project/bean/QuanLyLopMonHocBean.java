/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.bean;

import itplus.project.entity.GioHocEntity;
import itplus.project.entity.LopHocEntity;
import itplus.project.entity.LopMonHocEntity;
import itplus.project.entity.MonHocEntity;
import itplus.project.entity.PhongHocEntity;
import itplus.project.entity.SinhVienEntity;
import itplus.project.model.LopHocModel;
import itplus.project.model.LopMonHocModel;
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
public class QuanLyLopMonHocBean extends MessageUtil {

    private String focus = "txtLopHoc";
    private LopHocModel lopHocModel;
    private LopHocEntity lopHocEntity;
    private ArrayList<LopHocEntity> arrLopHoc;
    private Map<String, String> lopHocList = new HashMap<String, String>();

    private MonHocEntity monHocEntity;
    private MonHocModel monHocModel;
    private ArrayList<MonHocEntity> arrMonHoc;
    private Map<String, String> monHocList = new HashMap<String, String>();

    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();

    private GioHocEntity gioHocEntity;
    private ArrayList<GioHocEntity> arrGioHoc;
    private Map<String, String> gioHocList = new HashMap<String, String>();

    private PhongHocEntity phongHocEntity;
    private ArrayList<PhongHocEntity> arrPhongHoc;
    private Map<String, String> phongHocList = new HashMap<String, String>();

    private LopMonHocEntity lopMonHocEntity, rowSelected;
    private LopMonHocModel lopMonHocModel;
    private ArrayList<LopMonHocEntity> arrLopMonHoc, listLopMonHocSelected;
    private String[] arrNgayHoc;
    private boolean checkUpdate;
    private boolean eventSelected = false;

    /**
     * Creates a new instance of QuanLyLopMonHocBean
     */
    public QuanLyLopMonHocBean() {
        lopHocModel = new LopHocModel();
        lopHocEntity = new LopHocEntity();
        monHocEntity = new MonHocEntity();
        monHocModel = new MonHocModel();
        gioHocEntity = new GioHocEntity();
        phongHocEntity = new PhongHocEntity();
        lopMonHocEntity = new LopMonHocEntity();
        lopMonHocModel = new LopMonHocModel();
        getAllLopHoc();
        getAllPhongHoc();
        getAllGioHoc();
        getAllLopMonHoc();
    }

    public void getAllLopMonHoc() {
        try {
            arrLopMonHoc = new ArrayList<LopMonHocEntity>();
            arrLopMonHoc = lopMonHocModel.getAllLopMonHoc();
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(QuanLyLopMonHocBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addLopMonHoc() {
        checkUpdate = true;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if (isValidate()) {
            try {
                int id = 0;
                String NgayHoc = "";
                for (int i = 0; i < arrNgayHoc.length; i++) {
                    NgayHoc += arrNgayHoc[i] + " ";
                }
                System.out.println("Ngày Hoc: " + NgayHoc);
                lopMonHocEntity.setNgayHoc(NgayHoc);

                if (lopMonHocEntity.getNgayNghiDKBatDau() != null && lopMonHocEntity.getNgayNghiDKKetThuc() != null) {
                    id = lopMonHocModel.addLopMonHocNgayNghi(lopMonHocEntity);
                } else {
                    id = lopMonHocModel.addLopMonHoc(lopMonHocEntity);
                }

                // update len giao dien
                // lay ve ten lop
                String TenLop = lopMonHocModel.getTenLop(lopMonHocEntity.getMaLop());
                lopMonHocEntity.setTenLop(TenLop);
                String TenMon = lopMonHocModel.getTenMon(lopMonHocEntity.getMaMonHoc());
                lopMonHocEntity.setTenMonHoc(TenMon);
                String GioHoc = lopMonHocModel.getGioHoc(lopMonHocEntity.getMaGioHoc());
                lopMonHocEntity.setThoiGian(GioHoc);
                String PhongHoc = lopMonHocModel.getTenPhongHoc(lopMonHocEntity.getMaPhong());
                lopMonHocEntity.setTenPhongHoc(PhongHoc);

                lopMonHocEntity.setNgayBatDauHocView(format.format(lopMonHocEntity.getNgayBatDauHoc()));
                lopMonHocEntity.setNgayThiDuKienView(format.format(lopMonHocEntity.getNgayThiDuKien()));
                if (lopMonHocEntity.getNgayNghiDKBatDau() == null) {
                    lopMonHocEntity.setNgayNghiDKBatDauView("Không");
                } else {
                    lopMonHocEntity.setNgayNghiDKBatDauView(format.format(lopMonHocEntity.getNgayNghiDKBatDau()));
                }

                if (lopMonHocEntity.getNgayNghiDKKetThuc() == null) {
                    lopMonHocEntity.setNgayNghiDKKetThucView("Không");
                } else {
                    lopMonHocEntity.setNgayNghiDKKetThucView(format.format(lopMonHocEntity.getNgayNghiDKKetThuc()));
                }

                arrLopMonHoc.add(0,lopMonHocEntity);

                lopMonHocEntity = new LopMonHocEntity();
                focus = "txtLopHoc";
                addSuccessMessage("Thêm thành công");
            } catch (SQLException ex) {
                System.out.println(ex);
                Logger.getLogger(QuanLyLopMonHocBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyLopMonHocBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void editLopMonHoc() {
        checkUpdate = false;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if (isValidate()) {
            try {
//                lopMonHocEntity.setMaLop(lopHoc);
//                lopMonHocEntity.setMaMonHoc(monHoc);
                lopMonHocEntity.setMaGioHoc(lopMonHocEntity.getMaGioHoc());
                lopMonHocEntity.setMaPhong(lopMonHocEntity.getMaPhong());
                String NgayHoc = "";
                for (int i = 0; i < arrNgayHoc.length; i++) {
                    NgayHoc += arrNgayHoc[i] + " ";
                }
                System.out.println("Ngày Hoc: " + NgayHoc);
                lopMonHocEntity.setNgayHoc(NgayHoc);

                if (lopMonHocEntity.getNgayNghiDKBatDau() != null || lopMonHocEntity.getNgayNghiDKKetThuc() != null) {
                    if (lopMonHocEntity.getNgayNghiDKKetThuc() == null) {
                        addErrorMessage("Bạn chưa nhập ngày nghỉ kết thúc");

                    } else if (lopMonHocEntity.getNgayNghiDKBatDau() == null) {
                        addErrorMessage("Bạn chưa nhập ngày nghỉ bắt đầu");
                    } else {
                        System.out.println("sua lop mon hoc full");
                        lopMonHocModel.editLopMonHocFull(lopMonHocEntity);
                    }
                } else {
                    System.out.println("sua lop mon hoc ko co ngay nghi");
                    lopMonHocModel.editLopMonHoc(lopMonHocEntity);
                }

                // cap nhat tren giao dien
                String TenLop = lopMonHocModel.getTenLop(lopMonHocEntity.getMaLop());
                lopMonHocEntity.setTenLop(TenLop);
                String TenMon = lopMonHocModel.getTenMon(lopMonHocEntity.getMaMonHoc());
                lopMonHocEntity.setTenMonHoc(TenMon);
                String GioHoc = lopMonHocModel.getGioHoc(lopMonHocEntity.getMaGioHoc());
                lopMonHocEntity.setThoiGian(GioHoc);
                String PhongHoc = lopMonHocModel.getTenPhongHoc(lopMonHocEntity.getMaPhong());
                lopMonHocEntity.setTenPhongHoc(PhongHoc);

                lopMonHocEntity.setNgayBatDauHocView(format.format(lopMonHocEntity.getNgayBatDauHoc()));
                lopMonHocEntity.setNgayThiDuKienView(format.format(lopMonHocEntity.getNgayThiDuKien()));
                if (lopMonHocEntity.getNgayNghiDKBatDau() == null) {
                    lopMonHocEntity.setNgayNghiDKBatDauView("Không");
                } else {
                    lopMonHocEntity.setNgayNghiDKBatDauView(format.format(lopMonHocEntity.getNgayNghiDKBatDau()));
                }

                if (lopMonHocEntity.getNgayNghiDKKetThuc() == null) {
                    lopMonHocEntity.setNgayNghiDKKetThucView("Không");
                } else {
                    lopMonHocEntity.setNgayNghiDKKetThucView(format.format(lopMonHocEntity.getNgayNghiDKKetThuc()));
                }

                for (int i = 0; i < arrLopMonHoc.size(); i++) {
                    if (arrLopMonHoc.get(i).getIdLopMonHoc() == lopMonHocEntity.getIdLopMonHoc()) {
                        arrLopMonHoc.set(i, lopMonHocEntity);
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(QuanLyLopMonHocBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(QuanLyLopMonHocBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            focus = "txtLopHoc";
            addSuccessMessage("Sửa thành công");

        }
    }

    public boolean isValidate() {
        boolean checkDuplicateThoiKhoaBieu = false;
        if (checkUpdate) {

            try {
                checkDuplicateThoiKhoaBieu = lopMonHocModel.checkDuplicateThoiKhoaBieu(lopMonHocEntity.getMaLop(), lopMonHocEntity.getMaMonHoc());
            } catch (Exception ex) {
                Logger.getLogger(QuanLyLopMonHocBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ValidatorUtil.isSelector(lopMonHocEntity.getMaLop())) {
                addErrorMessage("Bạn chưa chọn lớp học");
                focus = "txtLopHoc";
                return false;
            } else if (ValidatorUtil.isSelector(lopMonHocEntity.getMaMonHoc())) {
                addErrorMessage("Bạn chưa chọn môn học");
                focus = "sMonHoc";
                return false;
            }
        }

        if (ValidatorUtil.isSelector(lopMonHocEntity.getMaGioHoc())) {
            addErrorMessage("Bạn chưa chọn giờ học");
            focus = "txtGioHoc";
            return false;
        } else if (ValidatorUtil.isSelector(lopMonHocEntity.getMaPhong())) {
            addErrorMessage("Bạn chưa chọn phòng học");
            focus = "txtPhongHoc";
            return false;
        } else if (ValidatorUtil.isSpaceString(lopMonHocEntity.getGiangVien())) {
            addErrorMessage("Chưa nhập tên giảng viên");
            focus = "txtGiangVien";
            return false;
        } else if (arrNgayHoc.length == 0) {
            addErrorMessage("Bạn chưa chọn ngày học");
            return false;
        } else if (lopMonHocEntity.getNgayBatDauHoc() == null) {
            addErrorMessage("Bạn chưa nhập ngày bắt đầu học");
            focus = "txtNgayBauDauHoc";
            return false;
        } else if (lopMonHocEntity.getNgayThiDuKien() == null) {
            addErrorMessage("Bạn chưa nhập ngày thi dự kiến");
            focus = "txtNgayThi";
            return false;

        } else if (lopMonHocEntity.getNgayBatDauHoc().after(lopMonHocEntity.getNgayThiDuKien())) {
            addErrorMessage("Ngày thi phải sau ngày bắt đầu học");
            focus = "txtNgayThi";
            return false;
        } else if (checkDuplicateThoiKhoaBieu) {
            addErrorMessage("Môn này đã được tạo thời khóa biểu");
            focus = "txtLopHoc";
            return false;
        } else if (lopMonHocEntity.getNgayNghiDKBatDau() != null && lopMonHocEntity.getNgayNghiDKKetThuc() == null) {
            addErrorMessage("Chưa nhập ngày nghỉ kết thúc");
            focus = "txtNgayNghiKT";
            return false;
        } else if (lopMonHocEntity.getNgayNghiDKBatDau() == null && lopMonHocEntity.getNgayNghiDKKetThuc() != null) {
            addErrorMessage("Chưa nhập ngày nghỉ bắt đầu");
            focus = "txtNgayNghiBD";
            return false;
        } else if (lopMonHocEntity.getNgayNghiDKBatDau() != null && lopMonHocEntity.getNgayNghiDKKetThuc() != null) {
            if (lopMonHocEntity.getNgayNghiDKBatDau().after(lopMonHocEntity.getNgayNghiDKKetThuc())) {
                addErrorMessage("Ngày nghỉ kết thúc phải sau ngày nghỉ bắt đầu");
                focus = "txtNgayNghiKT";
                return false;
            } else if (lopMonHocEntity.getNgayBatDauHoc().after(lopMonHocEntity.getNgayNghiDKBatDau())) {
                addErrorMessage("Ngày nghỉ ko được trước hoặc trùng ngày bắt đầu học");
                focus = "txtNgayNghiBD";
                return false;
            }
        }else {
            return true;
        }
        
        return true;
    }

    public void deleteLopMonHoc() {
        try {
            lopMonHocModel.deleteLopMonHoc(listLopMonHocSelected);
            // xoa trong bang ngay hoc
//            lopMonHocModel.deleteNgayHoc(listLopMonHocSelected);

            //goi ham xoa ben model
            //xoa tren giao dien
            for (LopMonHocEntity lopMonHoc : listLopMonHocSelected) {
                arrLopMonHoc.remove(lopMonHoc);
            }
//            checkStatusButton();//ham enable hoac disable button tren giao dien
            lopMonHocEntity = new LopMonHocEntity();
            addSuccessMessage("Xóa thành công");
        } catch (Exception ex) {
            addErrorMessage(ex);
        }
    }

    public void getAllPhongHoc() {
        try {
            arrPhongHoc = new ArrayList<PhongHocEntity>();
            arrPhongHoc = lopMonHocModel.getAllPhongHoc();
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(QuanLyLopMonHocBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        phongHocList = new HashMap<String, String>();
        for (int i = 0; i < arrPhongHoc.size(); i++) {
            phongHocList.put(arrPhongHoc.get(i).getTenPhongHoc(), arrPhongHoc.get(i).getMaPhongHoc());
        }

    }

    public void getAllGioHoc() {
        try {
            arrGioHoc = new ArrayList<GioHocEntity>();
            arrGioHoc = lopMonHocModel.getAllGioHoc();
        } catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(QuanLyLopMonHocBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        gioHocList = new HashMap<String, String>();
        for (int i = 0; i < arrGioHoc.size(); i++) {
            gioHocList.put(arrGioHoc.get(i).getThoiGian(), arrGioHoc.get(i).getMaGioHoc());
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

    public void clearText() {
        eventSelected = false;
        lopMonHocEntity = new LopMonHocEntity();
    }

    public void selectedRowtable(SelectEvent event) {
        eventSelected = true;
        rowSelected = (LopMonHocEntity) event.getObject();
        try {
            lopMonHocEntity = rowSelected.clone();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("idLopMonHoc: " + lopMonHocEntity.getIdLopMonHoc());
    }

    public String checkSelectedTable() {
        if (eventSelected) {
            return "true";
        } else {
            return "false";
        }
    }

    public Map<String, Map<String, String>> getDataMonHoc() {
        return data;
    }

    public void onKhoaHocChange() {
        ArrayList<MonHocEntity> monHocSelected = new ArrayList<MonHocEntity>();
        try {
            monHocSelected = monHocModel.getAllMonHocFormMaLop(lopMonHocEntity.getMaLop());
            Map<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < monHocSelected.size(); i++) {
                map.put(monHocSelected.get(i).getTenMonHoc(), monHocSelected.get(i).getMaMonHoc());
            }
            data.put(lopMonHocEntity.getMaLop(), map);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            Logger.getLogger(QuanLyLopBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (lopMonHocEntity.getMaLop() != null && !lopMonHocEntity.getMaLop().equals("")) {
            monHocList = data.get(lopMonHocEntity.getMaLop());
        } else {
            monHocList = new HashMap<String, String>();
        }
    }

    public String checkStatusButton() {
        if (arrLopMonHoc.isEmpty()) {
            return "true";
        } else {
            return "false";
        }
    }

    ////// getter and setter
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

    public GioHocEntity getGioHocEntity() {
        return gioHocEntity;
    }

    public void setGioHocEntity(GioHocEntity gioHocEntity) {
        this.gioHocEntity = gioHocEntity;
    }

    public ArrayList<GioHocEntity> getArrGioHoc() {
        return arrGioHoc;
    }

    public void setArrGioHoc(ArrayList<GioHocEntity> arrGioHoc) {
        this.arrGioHoc = arrGioHoc;
    }

    public Map<String, String> getGioHocList() {
        return gioHocList;
    }

    public void setGioHocList(Map<String, String> gioHocList) {
        this.gioHocList = gioHocList;
    }

    public PhongHocEntity getPhongHocEntity() {
        return phongHocEntity;
    }

    public void setPhongHocEntity(PhongHocEntity phongHocEntity) {
        this.phongHocEntity = phongHocEntity;
    }

    public ArrayList<PhongHocEntity> getArrPhongHoc() {
        return arrPhongHoc;
    }

    public void setArrPhongHoc(ArrayList<PhongHocEntity> arrPhongHoc) {
        this.arrPhongHoc = arrPhongHoc;
    }

    public Map<String, String> getPhongHocList() {
        return phongHocList;
    }

    public void setPhongHocList(Map<String, String> phongHocList) {
        this.phongHocList = phongHocList;
    }

    public LopMonHocEntity getLopMonHocEntity() {
        return lopMonHocEntity;
    }

    public void setLopMonHocEntity(LopMonHocEntity lopMonHocEntity) {
        this.lopMonHocEntity = lopMonHocEntity;
    }

    public LopMonHocModel getLopMonHocModel() {
        return lopMonHocModel;
    }

    public void setLopMonHocModel(LopMonHocModel lopMonHocModel) {
        this.lopMonHocModel = lopMonHocModel;
    }

    public String[] getArrNgayHoc() {
        return arrNgayHoc;
    }

    public void setArrNgayHoc(String[] arrNgayHoc) {
        this.arrNgayHoc = arrNgayHoc;
    }

    public String getFocus() {
        return focus;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public LopMonHocEntity getRowSelected() {
        return rowSelected;
    }

    public void setRowSelected(LopMonHocEntity rowSelected) {
        this.rowSelected = rowSelected;
    }

    public ArrayList<LopMonHocEntity> getArrLopMonHoc() {
        return arrLopMonHoc;
    }

    public void setArrLopMonHoc(ArrayList<LopMonHocEntity> arrLopMonHoc) {
        this.arrLopMonHoc = arrLopMonHoc;
    }

    public ArrayList<LopMonHocEntity> getListLopMonHocSelected() {
        return listLopMonHocSelected;
    }

    public void setListLopMonHocSelected(ArrayList<LopMonHocEntity> listLopMonHocSelected) {
        this.listLopMonHocSelected = listLopMonHocSelected;
    }

}
