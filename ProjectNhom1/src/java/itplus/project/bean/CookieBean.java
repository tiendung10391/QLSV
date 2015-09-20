/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.bean;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dung NT
 */
public class CookieBean implements Serializable {

    /**
     * Creates a new instance of CookieBean
     */
    public CookieBean() {
    }

    public static Cookie getCookie(String name) {
        Cookie loginCookie = null;
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    loginCookie = cookie;
                    break;
                }
            }
        }
        return loginCookie;
    }

    public static Cookie addCookie(String name, String value, int time) {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(time);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return cookie;
    }

    public static void removeCookie(String name) {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
            }
        }
    }

    public String getUsernameLogin() {
        Cookie loginCookie = getCookie("username");
        String username = loginCookie.getValue();
        return username;
    }

    public String getIdUserLogin() {
        Cookie loginCookie = getCookie("id");
        String id = loginCookie.getValue();
        return id;
    }

}
