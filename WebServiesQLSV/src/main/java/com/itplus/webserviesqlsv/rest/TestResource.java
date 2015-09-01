/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author Dung NT
 */
@Path("test")
public class TestResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TestResource
     */
    public TestResource() {
    }

    /**
     * Retrieves representation of an instance of com.itplus.webserviesqlsv.rest.TestResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getText() {
        //TODO return proper representation object
        return "test";
    }

    /**
     * PUT method for updating or creating an instance of TestResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putText(String content) {
    }
}
