/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.bean;

import itplus.project.entity.LoginEntity;
import itplus.project.model.LoginModel;
import itplus.project.util.MessageUtil;
import itplus.project.util.ValidatorUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dung NT
 */
@ManagedBean
@ViewScoped
public class LoginBean extends MessageUtil {

    private LoginEntity loginEntity;
    private String Focus = "txtUsername";
    LoginModel loginModel;
    boolean checkLogin;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        loginEntity = new LoginEntity();
        loginModel = new LoginModel();
    }

    public String checkLogin() {
        if (isValidate()) {
            HttpSession session = SessionBean.newSession(true); // khoi tao session new chua cho session tao session moi
            session.setAttribute("username", loginEntity.getUsername());
            session.setAttribute("password", loginEntity.getPassword());
            
            if(loginEntity.isRemember()){
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                Cookie user = new Cookie("username", loginEntity.getUsername());
                System.out.println(">>>>>>>>addCookie");
                user.setMaxAge(60 * 60 * 48);
                user.setPath("/");
                user.setHttpOnly(true);
                response.addCookie(user);
            }
            return "/admin/backend_nguoidung?faces-redirect=true";
        } else {
            return "";
        }
    }

    public String logOut() {
        //hủy session
        HttpSession session = SessionBean.newSession(false);
        session.invalidate();

        //hủy cookie
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        Cookie[] cookie = request.getCookies();
        for (int i = 0; i < cookie.length; i++) {
            cookie[i].setMaxAge(0);
            cookie[i].setPath("/");
            cookie[i].setHttpOnly(true);
            response.addCookie(cookie[i]);
        }

        return "/pages/index?faces-redirect=true";
    }

    public boolean isValidate() {
        try {
            checkLogin = loginModel.checkLogin(loginEntity.getUsername(), loginEntity.getPassword());
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!ValidatorUtil.isSpaceString(loginEntity.getUsername())) {
            addErrorMessage("Bạn chưa nhập tên đăng nhập!");
            Focus = "txtUsername";
            return false;
        } else if (!ValidatorUtil.isSpaceString(loginEntity.getPassword())) {
            addErrorMessage("Bạn chưa nhập mật khẩu");
            Focus = "txtPassword";
            return false;
        } else if (!checkLogin) {
            addErrorMessage("Bạn nhập sai tên đăng nhập hoặc mật khẩu");
            loginEntity = new LoginEntity();
            Focus = "txtUsername";
            return false;
        } else {
            return true;
        }
    }

    public void clearText() {
        try {
            loginEntity = new LoginEntity();
            Focus = "txtUsername";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }

    public LoginEntity getLoginEntity() {
        return loginEntity;
    }

    public void setLoginEntity(LoginEntity loginEntity) {
        this.loginEntity = loginEntity;
    }

    public String getFocus() {
        return Focus;
    }

    public void setFocus(String Focus) {
        this.Focus = Focus;
    }

}
