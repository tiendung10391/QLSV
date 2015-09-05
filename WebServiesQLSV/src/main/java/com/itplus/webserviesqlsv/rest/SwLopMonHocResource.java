/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.rest;

import com.itplus.webserviesqlsv.Entity.DiemEntity;
import com.itplus.webserviesqlsv.Entity.LopMonHocEntity;
import com.itplus.webserviesqlsv.Model.LopHocModel;
import com.itplus.webserviesqlsv.Model.LopMonHocModel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
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
@Path("SwLopMonHoc")
public class SwLopMonHocResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SwLopMonHocResource
     */
    LopMonHocModel lopMonHocModel;
    public SwLopMonHocResource() {
        lopMonHocModel = new LopMonHocModel();
    }

    /**
     * Retrieves representation of an instance of com.itplus.webserviesqlsv.rest.SwLopMonHocResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllLopMonHoc")
    public ArrayList<LopMonHocEntity> getAllDiem() {
        ArrayList<LopMonHocEntity> arrLopMonHoc = null;
        try {
            arrLopMonHoc = new ArrayList<LopMonHocEntity>();
            arrLopMonHoc = lopMonHocModel.getLopMonHoc();
        } catch (Exception ex) {
            Logger.getLogger(SwKhoaHocResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrLopMonHoc;
    }
    
     // them lop hoc
    @POST
    @Path("/addLopMonHoc")
    public String addDiem(@FormParam("MaMonHoc") String MaMonHoc, @FormParam("MaLop") String MaLop,
            @FormParam("MaGioHoc") String MaGioHoc, @FormParam("MaPhong") String MaPhong,
            @FormParam("MaHocKy") String MaHocKy) throws Exception {

        try {
            LopMonHocEntity lopMonHoc = new LopMonHocEntity();
            lopMonHoc.setMaMonHoc(MaMonHoc);
            lopMonHoc.setMaLop(MaLop);
            lopMonHoc.setMaGioHoc(MaGioHoc);
            lopMonHoc.setMaPhongHoc(MaPhong);
            lopMonHoc.setMaHocKy(MaHocKy);
            
            lopMonHocModel.addLopmonhoc(lopMonHoc);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return "success";
    }

    /**
     * PUT method for updating or creating an instance of SwLopMonHocResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
