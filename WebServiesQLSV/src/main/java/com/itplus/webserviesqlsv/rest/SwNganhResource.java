/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.rest;

import com.itplus.webserviesqlsv.Entity.NganhEntity;
import com.itplus.webserviesqlsv.Model.NganhModel;
import java.util.ArrayList;
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
@Path("swNganh")
public class SwNganhResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SwNganhResource
     */
    NganhModel nganhModel;
    public SwNganhResource() {
        nganhModel = new NganhModel();
    }

    /**
     * Retrieves representation of an instance of com.itplus.webserviesqlsv.rest.SwNganhResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllNganh")
    public ArrayList<NganhEntity> getAllNganh() {
        ArrayList<NganhEntity> arrNganh = null;
        try {
            arrNganh = new ArrayList<NganhEntity>();
             arrNganh = nganhModel.getAllNganh();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return arrNganh;
    }

    /**
     * PUT method for updating or creating an instance of SwNganhResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
