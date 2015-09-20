/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.util;

import itplus.project.bean.SessionBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dung NT
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            String username = (String) req.getSession().getAttribute("username");
            if (username != null) {
                chain.doFilter(req, res);
            } else {
                Cookie loginCookie = null;
                Cookie[] cookies = req.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("username")) {
                            loginCookie = cookie;
                            HttpSession session = req.getSession(true);
                            session.setAttribute("username", cookie.getValue());

                            break;
                        }
                    }

                }

                if (loginCookie != null) {
                    chain.doFilter(req, res);
                } else {
                    String contextPath = req.getContextPath();
                    res.sendRedirect(contextPath + "/faces/pages/index.xhtml");
                }
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        //kiem tra seession co ton tai khong
    }

    @Override
    public void destroy() {

    }

}
