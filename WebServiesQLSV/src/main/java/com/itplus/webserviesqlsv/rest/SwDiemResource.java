/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.rest;

import com.itplus.webserviesqlsv.Entity.DiemEntity;
import com.itplus.webserviesqlsv.Entity.KhoaHocEntity;
import com.itplus.webserviesqlsv.Entity.LopHocEntity;
import com.itplus.webserviesqlsv.Model.DiemModel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Dung NT
 */
@Path("SwDiem")
public class SwDiemResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SwDiemResource
     */
    DiemModel diemModel;
    public SwDiemResource() {
        diemModel = new DiemModel();
    }

    /**
     * Retrieves representation of an instance of com.itplus.webserviesqlsv.rest.SwDiemResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAllDiem")
    public ArrayList<DiemEntity> getAllDiem() {
        ArrayList<DiemEntity> arrDiem = null;
        try {
            arrDiem = new ArrayList<DiemEntity>();
            arrDiem = diemModel.getDiem();
        } catch (Exception ex) {
            Logger.getLogger(SwKhoaHocResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrDiem;
    }
    
    // them lop hoc
    @POST
    @Path("/addDiem")
    public String addDiem(@FormParam("MaMonHoc") String MaMonHoc, @FormParam("MaSV") String MaSV,
            @FormParam("DiemLan1") int DiemLan1, @FormParam("DiemLan2") int DiemLan2,
            @FormParam("DiemLan3") int DiemLan3, @FormParam("TrangThai") boolean TrangThai) throws Exception {

        try {
            DiemEntity diem = new DiemEntity();
            diem.setMaMonHoc(MaMonHoc);
            diem.setMaSV(MaSV);
            diem.setDiemLan1(DiemLan1);
            diem.setDiemlan2(DiemLan2);
            diem.setDiemLan3(DiemLan3);
            diem.setTrangThai(TrangThai);
            diemModel.addDiem(diem);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return "success";
    }
    
    // sua lop hoc
    @PUT
    @Path("/editDiem")
    public String editDiem(@FormParam("MaMonHoc") String MaMonHoc, @FormParam("MaSV") String MaSV,
            @FormParam("DiemLan1") int DiemLan1, @FormParam("DiemLan2") int DiemLan2,
            @FormParam("DiemLan3") int DiemLan3, @FormParam("TrangThai") boolean TrangThai) throws Exception{
        try{
            DiemEntity diem = new DiemEntity();
            diem.setMaMonHoc(MaMonHoc);
            diem.setMaSV(MaSV);
            diem.setDiemLan1(DiemLan1);
            diem.setDiemlan2(DiemLan2);
            diem.setDiemLan3(DiemLan3);
            diem.setTrangThai(TrangThai);
            diemModel.editDiem(diem);
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        return "edit success";
    }
    
    // xoa lop hoc
    @DELETE
    @Path("/removeDiem")
    public String removeDiem(@FormParam("MaMonHoc")String MaMonHoc, @FormParam("MaSV") String MaSV) throws Exception{
        try{
            diemModel.deleteDiem(MaMonHoc, MaSV);
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        return "remove success";
    }

    /**
     * PUT method for updating or creating an instance of SwDiemResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}