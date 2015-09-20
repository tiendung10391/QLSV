/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itplus.webserviesqlsv.rest;

import com.itplus.webserviesqlsv.Entity.AdChuongTrinhEntity;
import com.itplus.webserviesqlsv.Entity.AdLichHocEntity;
import com.itplus.webserviesqlsv.Model.AdLichHocModel;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Administrator
 */
@Path("SwAdLichHoc")
public class SwAdLichHocResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SwAdLichHocResource
     */
    AdLichHocModel alhm;
    public SwAdLichHocResource() {
        alhm = new AdLichHocModel();
    }

    /**
     * Retrieves representation of an instance of com.itplus.webserviesqlsv.rest.SwAdLichHocResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAllLichHoc_Ad")
    public ArrayList<AdLichHocEntity> getAllMonHoc_Ad(@QueryParam("MaLop") String lop) {
        ArrayList<AdLichHocEntity> arrNganh = null;
        try {
            arrNganh = new ArrayList<>();
            arrNganh = alhm.getLich(lop);
        } catch (Exception ex) {
            Logger.getLogger(SwKhoaHocResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrNganh;
    }

    /**
     * PUT method for updating or creating an instance of SwAdLichHocResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
