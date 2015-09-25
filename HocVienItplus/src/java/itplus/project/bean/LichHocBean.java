/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.bean;

import itplus.project.entity.LichHocEntity;
import itplus.project.model.LichHocModel;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dung NT
 */
public class LichHocBean {

    /**
     * Creates a new instance of LichHocBean
     */
    private ArrayList<LichHocEntity> arrLichHoc;
    private LichHocModel lichHocModel;
    private LichHocEntity lichHocEntity;
    
    public LichHocBean() {
        lichHocEntity = new LichHocEntity();
        lichHocModel = new LichHocModel();
        getAllLichHoc();
    }
    
    public void getAllLichHoc(){
        arrLichHoc = new ArrayList<LichHocEntity>();
        
        HttpSession session = SessionBean.newSession(false);
        String MaSV = (String) session.getAttribute("username");
        
        String MaLop = lichHocModel.getMaLop(MaSV);
        
        arrLichHoc = lichHocModel.getAllLichHoc(MaLop);
        
    }

    public ArrayList<LichHocEntity> getArrLichHoc() {
        return arrLichHoc;
    }

    public void setArrLichHoc(ArrayList<LichHocEntity> arrLichHoc) {
        this.arrLichHoc = arrLichHoc;
    }

    public LichHocModel getLichHocModel() {
        return lichHocModel;
    }

    public void setLichHocModel(LichHocModel lichHocModel) {
        this.lichHocModel = lichHocModel;
    }

    public LichHocEntity getLichHocEntity() {
        return lichHocEntity;
    }

    public void setLichHocEntity(LichHocEntity lichHocEntity) {
        this.lichHocEntity = lichHocEntity;
    }
    
    
    
}
