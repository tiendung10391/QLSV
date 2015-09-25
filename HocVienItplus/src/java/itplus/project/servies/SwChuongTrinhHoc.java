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
 * Jersey REST client generated for REST resource:SwAdChuongTrinhResource
 * [SwAdChuongTrinh]<br>
 * USAGE:
 * <pre>
 *        SwChuongTrinhHoc client = new SwChuongTrinhHoc();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Dung NT
 */
public class SwChuongTrinhHoc {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/WebServiesQLSV/rest";

    public SwChuongTrinhHoc() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("SwAdChuongTrinh");
    }

    public <T> T getAllMonHoc_Ad(Class<T> responseType, String MaKhoaHoc) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (MaKhoaHoc != null) {
            resource = resource.queryParam("MaKhoaHoc", MaKhoaHoc);
        }
        resource = resource.path("getAllMonHoc_Ad");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String getMaKhoaHoc(String MaLop) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (MaLop != null) {
            resource = resource.queryParam("MaLop", MaLop);
        }
        resource = resource.path("getMaKhoaHoc");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public <T> T getAllKhoaHoc(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllKhoaHoc");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllHocKyFromMaKhoaHoc(Class<T> responseType, String MaKhoaHoc) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (MaKhoaHoc != null) {
            resource = resource.queryParam("MaKhoaHoc", MaKhoaHoc);
        }
        resource = resource.path("getHocKyFromMaKhoaHoc");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllChuongTrinhHocFromMaKhoaHoc(Class<T> responseType, String MaKhoaHoc) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (MaKhoaHoc != null) {
            resource = resource.queryParam("MaKhoaHoc", MaKhoaHoc);
        }
        resource = resource.path("getChuongTrinhFromMaKhoaHoc");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void putXml(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public <T> T getAllChuongTrinhHocFromMaSV(Class<T> responseType, String MaSV) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (MaSV != null) {
            resource = resource.queryParam("MaSV", MaSV);
        }
        resource = resource.path("getChuongTrinhFromMaSV");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
