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
 * Jersey REST client generated for REST resource:SwSinhVienResource
 * [SwSinhVien]<br>
 * USAGE:
 * <pre>
 *        SwLogin client = new SwLogin();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Dung NT
 */
public class SwLogin {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8084/WebServiesQLSV/rest";

    public SwLogin() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("SwSinhVien");
    }

    public <T> T getLoginSinhVien(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getloginSinhVien");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void putJson(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    public <T> T getInfoSinhVien(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getInfoSinhVien");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String editLopHoc() throws ClientErrorException {
        return webTarget.path("editSinhVien").request().put(null, String.class);
    }

    public String removeLopHoc() throws ClientErrorException {
        return webTarget.path("removeSinhVien").request().delete(String.class);
    }

    public String checkLogin(String MatKhau, String TaiKhoan) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (MatKhau != null) {
            resource = resource.queryParam("MatKhau", MatKhau);
        }
        if (TaiKhoan != null) {
            resource = resource.queryParam("TaiKhoan", TaiKhoan);
        }
        resource = resource.path("checkLogin");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String checkSinhVien(String MaSV) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (MaSV != null) {
            resource = resource.queryParam("MaSV", MaSV);
        }
        resource = resource.path("checkSinhvien");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public void close() {
        client.close();
    }
    
}
