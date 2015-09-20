/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.rest;

import com.itplus.webserviesqlsv.Entity.LopHocEntity;
import com.itplus.webserviesqlsv.Entity.SinhVienEntity;
import com.itplus.webserviesqlsv.Entity.Utility;
import com.itplus.webserviesqlsv.Model.SinhVienModel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Dung NT
 */
@Path("SwSinhVien")
public class SwSinhVienResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SwSinhVienResource
     */
    
    SinhVienModel sinhVienModel ;
    public SwSinhVienResource() {
        sinhVienModel = new SinhVienModel();
    }
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/checkLogin")
     public String checkLogin(@QueryParam("TaiKhoan") String uname, @QueryParam("MatKhau") String pwd) throws Exception{
        String response = "";
        if(sinhVienModel.checkLogin(uname, pwd)){
            response = Utility.constructJSON("login",true);
        }else{
            response = Utility.constructJSON("login", false, "Tài khoản hoặc mật khẩu sai!");
        }
    return response;        
    }
     
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/checkSinhvien")
     public String checkSinhVien(@QueryParam("MaSV") String masv){
        String response = "";
         try {
             response = sinhVienModel.checkSinhVien(masv);
         } catch (Exception e) {
             e.printStackTrace();
         }
    return response;        
    }

    /**
     * Retrieves representation of an instance of com.itplus.webserviesqlsv.rest.SwSinhVienResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getInfoSinhVien")
    public ArrayList<SinhVienEntity> getInfoSinhVien() {
        //TODO return proper representation object
        ArrayList<SinhVienEntity> arrSinhVien = null;
        try {
            arrSinhVien = new ArrayList<SinhVienEntity>();
            arrSinhVien = sinhVienModel.getInfoSinhVien();
        } catch (Exception ex) {
            Logger.getLogger(SwLopHocResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arrSinhVien;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getloginSinhVien")
    public ArrayList<SinhVienEntity> getLoginSinhVien() {
        //TODO return proper representation object
        ArrayList<SinhVienEntity> arrSinhVien = null;
        try {
            arrSinhVien = new ArrayList<SinhVienEntity>();
            arrSinhVien = sinhVienModel.getLoginSinhVien();
        } catch (Exception ex) {
            Logger.getLogger(SwLopHocResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arrSinhVien;
    }

    /**
     * PUT method for updating or creating an instance of SwSinhVienResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
