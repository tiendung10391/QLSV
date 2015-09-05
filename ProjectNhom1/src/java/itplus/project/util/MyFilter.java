/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        
        //kiem tra seession co ton tai khong
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String username = (String) req.getSession().getAttribute("username");
        if (username != null) {
            chain.doFilter(req, res);
        } else {
            //day ve trang login
            String contextPath = req.getContextPath();
            res.sendRedirect(contextPath + "/faces/admin/index.xhtml");
        }

    }

    @Override
    public void destroy() {

    }

}
