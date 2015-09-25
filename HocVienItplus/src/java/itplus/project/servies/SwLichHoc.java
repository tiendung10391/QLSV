/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.servies;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:SwAdLichHocResource
 * [SwAdLichHoc]<br>
 * USAGE:
 * <pre>
 *        SwLichHoc client = new SwLichHoc();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Dung NT
 */
public class SwLichHoc {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/WebServiesQLSV/rest";

    public SwLichHoc() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("SwAdLichHoc");
    }

    public <T> T getAllMonHoc_Ad(Class<T> responseType, String MaLop) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (MaLop != null) {
            resource = resource.queryParam("MaLop", MaLop);
        }
        resource = resource.path("getAllLichHoc_Ad");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getMaLop(Class<T> responseType, String MaSV) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (MaSV != null) {
            resource = resource.queryParam("MaSV", MaSV);
        }
        resource = resource.path("getMaLop");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void putXml(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void close() {
        client.close();
    }
    
}
