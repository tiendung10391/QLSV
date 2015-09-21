/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.bean;

import itplus.project.entity.SinhVienEntity;
import itplus.project.model.InfoModel;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dung NT
 */
public class InfoHocVienBean {

    private SinhVienEntity sinhVienEntity;
    ArrayList<SinhVienEntity> arrSinhVien;
    private InfoModel sinhVienModel;

    /**
     * Creates a new instance of InfoHocVienBean
     */
    public InfoHocVienBean() {
        sinhVienEntity = new SinhVienEntity();
        sinhVienModel = new InfoModel();
        getInfoHocVien();
    }

    public void getInfoHocVien() {

        arrSinhVien = new ArrayList<SinhVienEntity>();
        HttpSession session = SessionBean.newSession(false);
        String MaSV = (String) session.getAttribute("username");
        System.out.println("MaSV: " + MaSV);
        arrSinhVien = sinhVienModel.getInfoHocVien(MaSV);
        sinhVienEntity.setMaSinhVien(arrSinhVien.get(0).getMaSinhVien());
        sinhVienEntity.setSDT(arrSinhVien.get(0).getSDT());
        sinhVienEntity.setTenSinhVien(arrSinhVien.get(0).getTenSinhVien());
        sinhVienEntity.setDiaChi(arrSinhVien.get(0).getDiaChi());
        sinhVienEntity.setNgaySinhView(arrSinhVien.get(0).getNgaySinhView());
        sinhVienEntity.setTenLop(arrSinhVien.get(0).getTenLop());
        sinhVienEntity.setQueQuan(arrSinhVien.get(0).getQueQuan());
        sinhVienEntity.setEmail(arrSinhVien.get(0).getEmail());
        sinhVienEntity.setGioiTinh(arrSinhVien.get(0).getGioiTinh());
        System.out.println("ten sinh vien: " + arrSinhVien.get(0).getTenSinhVien());
        System.out.println("ten sinh vien entity: " + sinhVienEntity.getTenSinhVien());
    }

    public SinhVienEntity getSinhVienEntity() {
        return sinhVienEntity;
    }

    public void setSinhVienEntity(SinhVienEntity sinhVienEntity) {
        this.sinhVienEntity = sinhVienEntity;
    }

    public ArrayList<SinhVienEntity> getArrSinhVien() {
        return arrSinhVien;
    }

    public void setArrSinhVien(ArrayList<SinhVienEntity> arrSinhVien) {
        this.arrSinhVien = arrSinhVien;
    }

    public InfoModel getSinhVienModel() {
        return sinhVienModel;
    }

    public void setSinhVienModel(InfoModel sinhVienModel) {
        this.sinhVienModel = sinhVienModel;
    }

}
