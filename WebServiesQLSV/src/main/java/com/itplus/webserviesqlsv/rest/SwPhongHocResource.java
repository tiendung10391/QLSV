/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.rest;

import com.itplus.webserviesqlsv.Entity.MonHocEntity;
import com.itplus.webserviesqlsv.Entity.PhongHocEntity;
import com.itplus.webserviesqlsv.Model.PhongHocModel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Dung NT
 */
@Path("SwPhongHoc")
public class SwPhongHocResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SwPhongHocResource
     */
    PhongHocModel phongHocModel;
    public SwPhongHocResource() {
        phongHocModel = new PhongHocModel();
    }

    /**
     * Retrieves representation of an instance of com.itplus.webserviesqlsv.rest.SwPhongHocResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllPhongHoc")
    public ArrayList<PhongHocEntity> getAllPhongHoc() {
        ArrayList<PhongHocEntity> arrPhongHoc = null;
        try {
            arrPhongHoc = new ArrayList<PhongHocEntity>();
            arrPhongHoc = phongHocModel.getAllPhongHoc();
        } catch (Exception ex) {
            Logger.getLogger(SwKhoaHocResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrPhongHoc;
    }

    /**
     * PUT method for updating or creating an instance of SwPhongHocResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
