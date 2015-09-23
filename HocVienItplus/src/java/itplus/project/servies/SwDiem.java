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
 * Jersey REST client generated for REST resource:SwDiemResource [SwDiem]<br>
 * USAGE:
 * <pre>
 *        SwDiem client = new SwDiem();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Dung NT
 */
public class SwDiem {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/WebServiesQLSV/rest";

    public SwDiem() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("SwDiem");
    }

    public <T> T getAllDiemThiKhoaHoc(Class<T> responseType, String MaSV) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (MaSV != null) {
            resource = resource.queryParam("MaSV", MaSV);
        }
        resource = resource.path("getAllDiemThiKhoaHoc");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void putJson(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public String editDiem() throws ClientErrorException {
        return webTarget.path("editDiem").request().put(null, String.class);
    }

    public String removeDiem() throws ClientErrorException {
        return webTarget.path("removeDiem").request().delete(String.class);
    }

    public String addDiem() throws ClientErrorException {
        return webTarget.path("addDiem").request().post(null, String.class);
    }

    public <T> T getAllDiem(Class<T> responseType, String MaSV) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (MaSV != null) {
            resource = resource.queryParam("MaSV", MaSV);
        }
        resource = resource.path("getAllDiem");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllDiemThiTheoKy(Class<T> responseType, String MaSV) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (MaSV != null) {
            resource = resource.queryParam("MaSV", MaSV);
        }
        resource = resource.path("getAllDiemThiTheoKy");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
