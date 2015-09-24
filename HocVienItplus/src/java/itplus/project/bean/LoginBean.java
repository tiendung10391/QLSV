/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.bean;

import itplus.project.entity.SinhVienEntity;
import itplus.project.model.LoginModel;
import itplus.project.utils.MessageUtil;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Dung NT
 */
public class LoginBean extends MessageUtil {

    /**
     * Creates a new instance of LoginBean
     */
    private SinhVienEntity sinhVienEntity;
    ArrayList<SinhVienEntity> arrSinhVien;
    

    public LoginBean() {
        sinhVienEntity = new SinhVienEntity();
    }

    public String checkLogin(ActionEvent event) {
        boolean loggedIn = false;
        RequestContext context = RequestContext.getCurrentInstance();
        if (isValidate()) {
            System.out.println("login dung");
            loggedIn = true;
            HttpSession session = SessionBean.newSession(true);
            session.setAttribute("username", sinhVienEntity.getMaSinhVien());
            session.setAttribute("pass", sinhVienEntity.getMatKhau());
            context.addCallbackParam("loggedIn", loggedIn);
            return "/sinhvien/frontend_gochocvien?faces-redirect=true";
        } else {
            System.out.println("login sai");
            loggedIn = false;
            context.addCallbackParam("loggedIn", loggedIn);
            return "";
        }
    }
    
    public String logOut() {
        // Xoa session
        System.out.println("log out");
        HttpSession session = SessionBean.newSession(false);
        session.invalidate();
        return "/pages/index?faces-redirect=true";
    }

    public boolean isValidate() {
        try {
            arrSinhVien = new ArrayList<SinhVienEntity>();
            LoginModel login = new LoginModel();
            arrSinhVien = login.getLoginSinhVien();
            boolean checkLogin = false;
            for (int i = 0; i < arrSinhVien.size(); i++) {
                if (sinhVienEntity.getMaSinhVien().equals(arrSinhVien.get(i).getMaSinhVien()) && sinhVienEntity.getMatKhau().equals(arrSinhVien.get(i).getMatKhau())) {
                    checkLogin = true;
                    System.out.println("checkLogin: " + arrSinhVien.get(i).getMaSinhVien());
                }
            }

            if (!checkLogin) {
                addErrorMessage("Bạn nhập sai mật khẩu hoặc tên đăng nhập");
                sinhVienEntity = new SinhVienEntity();
                return false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;

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

}
