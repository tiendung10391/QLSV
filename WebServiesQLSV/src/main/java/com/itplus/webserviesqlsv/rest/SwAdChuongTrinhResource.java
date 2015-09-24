/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itplus.webserviesqlsv.rest;

import com.itplus.webserviesqlsv.Entity.AdChuongTrinhEntity;
import com.itplus.webserviesqlsv.Entity.ChuongTrinhEntity;
import com.itplus.webserviesqlsv.Entity.DiemEntity;
import com.itplus.webserviesqlsv.Entity.HocKyEntity;
import com.itplus.webserviesqlsv.Model.AdChuongTrinhModel;
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
@Path("SwAdChuongTrinh")
public class SwAdChuongTrinhResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SwAdChuongTrinhResource
     */
    AdChuongTrinhModel actm;
    ChuongTrinhEntity chuongTrinh;
    public SwAdChuongTrinhResource() {
        actm = new AdChuongTrinhModel();
    }

    /**
     * Retrieves representation of an instance of com.itplus.webserviesqlsv.rest.SwAdChuongTrinhResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getMaKhoaHoc")
    public String getMaKhoaHoc(@QueryParam("MaLop") String malop) {
        String manganh = "";
        try {
           manganh = actm.maKhoahoc(malop);
        } catch (Exception ex) {
            Logger.getLogger(SwKhoaHocResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return manganh;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAllMonHoc_Ad")
    public ArrayList<AdChuongTrinhEntity> getAllMonHoc_Ad(@QueryParam("MaKhoaHoc") String makhoahoc) {
        ArrayList<AdChuongTrinhEntity> arrNganh = null;
        try {
            arrNganh = new ArrayList<>();
            arrNganh = actm.getMon(makhoahoc);
        } catch (Exception ex) {
            Logger.getLogger(SwKhoaHocResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrNganh;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getChuongTrinhFromMaSV")
    public ArrayList<ChuongTrinhEntity> getAllChuongTrinhHocFromMaSV(@QueryParam("MaSV") String MaSV) {
        ArrayList<ChuongTrinhEntity> arrChuongTrinh = null;
        try {
            
            arrChuongTrinh = new ArrayList<ChuongTrinhEntity>();
            
            String MaKhoaHoc = actm.getMaKhoaHocFormMaSV(MaSV);
            System.out.println("MaKhoaHoc: " + MaKhoaHoc);
            arrChuongTrinh = actm.getAllChuongTrinhFromMaKhoaHoc(MaKhoaHoc);
        } catch (Exception ex) {
            Logger.getLogger(SwKhoaHocResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrChuongTrinh;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getChuongTrinhFromMaKhoaHoc")
    public ArrayList<ChuongTrinhEntity> getAllChuongTrinhHocFromMaKhoaHoc(@QueryParam("MaKhoaHoc") String MaKH) {
        ArrayList<ChuongTrinhEntity> arrChuongTrinh = null;
        try {
            
            arrChuongTrinh = new ArrayList<ChuongTrinhEntity>();
            
            arrChuongTrinh = actm.getAllChuongTrinhFromMaKhoaHoc(MaKH);
        } catch (Exception ex) {
            Logger.getLogger(SwKhoaHocResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrChuongTrinh;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getHocKyFromMaKhoaHoc")
    public ArrayList<HocKyEntity> getAllHocKyFromMaKhoaHoc(@QueryParam("MaKhoaHoc") String MaKH) {
        ArrayList<HocKyEntity> arr = null;
        try {
            
            arr = new ArrayList<HocKyEntity>();
            arr = actm.getAllHocKyFromMaKhoaHoc(MaKH);
        } catch (Exception ex) {
            Logger.getLogger(SwKhoaHocResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    

    /**
     * PUT method for updating or creating an instance of SwAdChuongTrinhResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
