/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.rest;

import com.itplus.webserviesqlsv.Entity.GioHocEntity;
import com.itplus.webserviesqlsv.Entity.PhongHocEntity;
import com.itplus.webserviesqlsv.Model.GioHocModel;
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
@Path("SwGioHoc")
public class SwGioHocResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SwGioHocResource
     */
    GioHocModel gioHocModel;
    public SwGioHocResource() {
        gioHocModel = new GioHocModel();
    }

    /**
     * Retrieves representation of an instance of com.itplus.webserviesqlsv.rest.SwGioHocResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllGioHoc")
    public ArrayList<GioHocEntity> getAllGioHoc() {
        ArrayList<GioHocEntity> arrGioHoc = null;
        try {
            arrGioHoc = new ArrayList<GioHocEntity>();
            arrGioHoc = gioHocModel.getGioHoc();
        } catch (Exception ex) {
            Logger.getLogger(SwKhoaHocResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrGioHoc;
    }

    /**
     * PUT method for updating or creating an instance of SwGioHocResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
