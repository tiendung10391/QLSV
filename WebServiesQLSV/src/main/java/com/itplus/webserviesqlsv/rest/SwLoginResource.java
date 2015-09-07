/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.rest;

import com.itplus.webserviesqlsv.Entity.loginEntity;
import com.itplus.webserviesqlsv.Model.LoginModel;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Dung NT
 */
@Path("SwLogin")
public class SwLoginResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SwLoginResource
     */
    LoginModel loginModel;
    public SwLoginResource() {
        loginModel = new LoginModel();
    }

    /**
     * Retrieves representation of an instance of com.itplus.webserviesqlsv.rest.SwLoginResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/checkLogin")
    public String checkLogin(@FormParam("TenDangNhap") String TenDangNhap, @FormParam("MatKhau") String MatKhau) {
        //TODO return proper representation object
        try{
            if(loginModel.checkLogin(TenDangNhap, MatKhau)){
                return "success";
            }else{
                return "error";
                       
            }
        }catch(Exception ex){
            System.out.println(ex.toString());
        }
        return "";
    }

    /**
     * PUT method for updating or creating an instance of SwLoginResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
