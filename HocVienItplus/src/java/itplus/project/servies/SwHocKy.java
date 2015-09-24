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
 * Jersey REST client generated for REST resource:SwHocKyResource [SwHocKy]<br>
 * USAGE:
 * <pre>
 *        SwHocKy client = new SwHocKy();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Dung NT
 */
public class SwHocKy {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/WebServiesQLSV/rest";

    public SwHocKy() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("SwHocKy");
    }

    public <T> T getAllDiemThiKhoaHoc(Class<T> responseType, String MaSV) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (MaSV != null) {
            resource = resource.queryParam("MaSV", MaSV);
        }
        resource = resource.path("getAllHocKy");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void putJson(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public void close() {
        client.close();
    }
    
}
