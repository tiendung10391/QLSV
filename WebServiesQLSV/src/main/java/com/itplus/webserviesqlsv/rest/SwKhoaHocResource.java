/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.rest;

import com.itplus.webserviesqlsv.Entity.KhoaHocEntity;
import com.itplus.webserviesqlsv.Model.KhoaHocModel;
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
@Path("swKhoaHoc")
public class SwKhoaHocResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SwKhoaHocResource
     */
    KhoaHocModel khoaHocModel;

    public SwKhoaHocResource() {
        khoaHocModel = new KhoaHocModel();
    }

    /**
     * Retrieves representation of an instance of
     * com.itplus.webserviesqlsv.rest.SwKhoaHocResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllKhoaHoc")
    public ArrayList<KhoaHocEntity> getAllKhoaHoc() {
        ArrayList<KhoaHocEntity> arrKhoaHoc = null;
        try {
            arrKhoaHoc = new ArrayList<KhoaHocEntity>();
            arrKhoaHoc = khoaHocModel.getAllKhoaHoc();
        } catch (Exception ex) {
            Logger.getLogger(SwKhoaHocResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrKhoaHoc;
    }

    /**
     * PUT method for updating or creating an instance of SwKhoaHocResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
