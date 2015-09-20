/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.rest;

import com.itplus.webserviesqlsv.Entity.LopHocEntity;
import com.itplus.webserviesqlsv.Model.LopHocModel;
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
@Path("swLopHoc")
public class SwLopHocResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SwLopHocResource
     */
    LopHocModel lopHocModel;

    public SwLopHocResource() {
        lopHocModel = new LopHocModel();
    }

    /**
     * Retrieves representation of an instance of
     * com.itplus.webserviesqlsv.rest.SwLopHocResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllLopHoc")
    public ArrayList<LopHocEntity> getAllLopHoc() {
        //TODO return proper representation object
        ArrayList<LopHocEntity> arrLopHoc = null;
        try {
            arrLopHoc = new ArrayList<LopHocEntity>();
            arrLopHoc = lopHocModel.getLophoc();
        } catch (Exception ex) {
            Logger.getLogger(SwLopHocResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arrLopHoc;

    }   

    /**
     * PUT method for updating or creating an instance of SwLopHocResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
