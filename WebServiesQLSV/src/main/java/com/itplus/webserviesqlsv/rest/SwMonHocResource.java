/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.rest;

import com.itplus.webserviesqlsv.Entity.DiemEntity;
import com.itplus.webserviesqlsv.Entity.MonHocEntity;
import com.itplus.webserviesqlsv.Model.MonHocModel;
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
@Path("SwMonHoc")
public class SwMonHocResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SwMonHocResource
     */
    MonHocModel monHocModel;
    public SwMonHocResource() {
        monHocModel = new MonHocModel();
    }

    /**
     * Retrieves representation of an instance of com.itplus.webserviesqlsv.rest.SwMonHocResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllMonHoc")
    public ArrayList<MonHocEntity> getAllMonHoc() {
        ArrayList<MonHocEntity> arrMonHoc = null;
        try {
            arrMonHoc = new ArrayList<MonHocEntity>();
            arrMonHoc = monHocModel.getMonhoc();
        } catch (Exception ex) {
            Logger.getLogger(SwKhoaHocResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrMonHoc;
    }
    
    // them lop hoc
    @POST
    @Path("/addMonHoc")
    public String addDiem(@FormParam("MaMonHoc") String MaMonHoc, @FormParam("TenMonHoc") String TenMonHoc,
            @FormParam("SoGio") int SoGio, @FormParam("MaKhoaHoc") String MaKhoaHoc) throws Exception {

        try {
            MonHocEntity monHoc = new MonHocEntity();
            monHoc.setTenMH(MaMonHoc);
            monHoc.setTenMH(TenMonHoc);
            monHoc.setSoGio(SoGio);
            monHoc.setMaKhoaHoc(MaKhoaHoc);
            monHocModel.addMonhoc(monHoc);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return "success";
    }
    
    // sua lop hoc
    @PUT
    @Path("/editMonHoc")
    public String editDiem(@FormParam("MaMonHoc") String MaMonHoc, @FormParam("TenMonHoc") String TenMonHoc,
            @FormParam("SoGio") int SoGio, @FormParam("MaKhoaHoc") String MaKhoaHoc) throws Exception{
        try{
             MonHocEntity monHoc = new MonHocEntity();
            monHoc.setTenMH(MaMonHoc);
            monHoc.setTenMH(TenMonHoc);
            monHoc.setSoGio(SoGio);
            monHoc.setMaKhoaHoc(MaKhoaHoc);
            monHocModel.editMonhoc(monHoc);
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        return "edit success";
    }
    
    // xoa lop hoc
    @DELETE
    @Path("/removeMonHoc")
    public String removeDiem(@FormParam("MaMonHoc")String MaMonHoc, @FormParam("MaKhoaHoc") String MaKhoaHoc) throws Exception{
        try{
            monHocModel.deleteMonhoc(MaMonHoc, MaKhoaHoc);
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
        return "remove success";
    }

    /**
     * PUT method for updating or creating an instance of SwMonHocResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
