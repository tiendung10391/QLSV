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
    @Path("/getAllSinhVien")
    public ArrayList<SinhVienEntity> getAllSinhVien() {
        //TODO return proper representation object
        ArrayList<SinhVienEntity> arrSinhVien = null;
        try {
            arrSinhVien = new ArrayList<SinhVienEntity>();
            arrSinhVien = sinhVienModel.getSinhVien();
        } catch (Exception ex) {
            Logger.getLogger(SwLopHocResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arrSinhVien;
    }
    
    // them lop hoc
    @POST
    @Path("/addSinhVien")
    public String addSinhVien(@FormParam("MaSV") String MaSV, @FormParam("TenSV") String TenSV,
            @FormParam("NgaySinh") String NgaySinh, @FormParam("GioiTinh") boolean GioiTinh,
            @FormParam("SDT") String SDT, @FormParam("DiaChi") String DiaChi, @FormParam("QueQuan") String QueQuan, 
            @FormParam("Email") String Email, @FormParam("Malop") String MaLop) throws Exception {

        try {
            SinhVienEntity sv = new SinhVienEntity();
            sv.setMaSV(MaSV);
            sv.setTenSV(TenSV);
            sv.setNgaySinh(NgaySinh);
            sv.setGioiTinh(GioiTinh);
            sv.setSdt(SDT);
            sv.setDiaChi(DiaChi);
            sv.setQueQuan(QueQuan);
            sv.setEmail(Email);
            sv.setMaLop(MaLop);
            sinhVienModel.addSinhVien(sv);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return "add success";
    }
    
    // sua sinh vien
    @PUT
    @Path("/editSinhVien")
    public String editLopHoc(@FormParam("MaSV") String MaSV, @FormParam("TenSV") String TenSV,
            @FormParam("NgaySinh") String NgaySinh, @FormParam("GioiTinh") boolean GioiTinh,
            @FormParam("SDT") String SDT, @FormParam("DiaChi") String DiaChi, @FormParam("QueQuan") String QueQuan, 
            @FormParam("Email") String Email, @FormParam("Malop") String MaLop) throws Exception{
        try{
            SinhVienEntity sv = new SinhVienEntity();
            sv.setMaSV(MaSV);
            sv.setTenSV(TenSV);
            sv.setNgaySinh(NgaySinh);
            sv.setGioiTinh(GioiTinh);
            sv.setSdt(SDT);
            sv.setDiaChi(DiaChi);
            sv.setQueQuan(QueQuan);
            sv.setEmail(Email);
            sv.setMaLop(MaLop);
            sinhVienModel.editSinhVien(sv);
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        return "edit success";
    }
    
    // xoa lop hoc
    @DELETE
    @Path("/removeSinhVien")
    public String removeLopHoc(@FormParam("MaSV")String MaSV) throws Exception{
        try{
            sinhVienModel.deleteSinhVien(MaSV);
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        return "remove success";
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
