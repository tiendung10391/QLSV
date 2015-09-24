/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.rest;

import com.itplus.webserviesqlsv.Entity.DiemEntity;
import com.itplus.webserviesqlsv.Entity.DiemThiEntity;
import com.itplus.webserviesqlsv.Entity.HocKyEntity;
import com.itplus.webserviesqlsv.Model.DiemModel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Dung NT
 */
@Path("SwHocKy")
public class SwHocKyResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SwHocKyResource
     */
    
    DiemModel diemModel;
    public SwHocKyResource() {
        diemModel = new DiemModel();
    }

    /**
     * Retrieves representation of an instance of com.itplus.webserviesqlsv.rest.SwHocKyResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllHocKy")
    public ArrayList<HocKyEntity> getAllDiemThiKhoaHoc(@QueryParam("MaSV") String MaSV) {
            ArrayList<HocKyEntity> arr = null;
        try {
            arr = new ArrayList<HocKyEntity>();
            arr = diemModel.getAllHocKy(MaSV);
        } catch (Exception ex) {
            Logger.getLogger(SwDiemResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    /**
     * PUT method for updating or creating an instance of SwHocKyResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
