/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.model;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import itplus.project.entity.SinhVienEntity;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ws.rs.core.MultivaluedMap;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author Dung NT
 */
public class InfoModel implements Serializable {

    ArrayList<SinhVienEntity> arrSinhVien = null;

    public ArrayList<SinhVienEntity> getInfoHocVien(String MaSV) {

        try {
            //test call restful webservices
            Client client = new Client();
            WebResource webResource = client.resource("http://localhost:8084/WebServiesQLSV/rest/SwSinhVien/getInfoSinhVien");
            MultivaluedMap queryParams = new MultivaluedMapImpl();
            queryParams.add("MaSV", MaSV);
//            Form form = new Form();
//            form.add("MaSV", MaSV);
            
            String s = webResource.queryParams(queryParams).get(String.class);
            System.out.println("url:" + s);
//            
//            WebResource ws = client.resource("http://localhost:8084/WebServiesQLSV/rest/SwSinhVien/getInfoSinhVien");
//            ClientResponse response = ws.accept("application/json").post(ClientResponse.class, queryParams);
//
//            if (response.getStatus() != 200) {
//                throw new RuntimeException("Failed : HTTP error code : "
//                        + response.getStatus());
//            }

//            String output = response.getEntity(String.class);

            try {
                JSONArray jsonArray = new JSONArray(s);

                JSONObject jsonObj = new JSONObject();  // these 4 files add jsonObject to jsonArrayF
                arrSinhVien = new ArrayList<SinhVienEntity>();
                int count = jsonArray.length(); // get totalCount of all jsonObjects
                for (int i = 0; i < count; i++) {   // iterate through jsonArray 
                    SinhVienEntity sv = new SinhVienEntity();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);  // get jsonObject @ i position 
                    System.out.println("jsonArray " + i + " : " + jsonObject.getString("maSV")); 
                    System.out.println("jsonArray " + i + " : " + jsonObject.getString("tenSV"));
                    sv.setMaSinhVien(jsonObject.getString("maSV"));
                    sv.setTenSinhVien(jsonObject.getString("tenSV"));
                    sv.setEmail(jsonObject.getString("email"));
                    sv.setTenKhoaHoc(jsonObject.getString("tenKhoaHoc"));
                    sv.setTenLop(jsonObject.getString("tenLop"));
                    sv.setNamNhapHoc(jsonObject.getString("namNhapHoc"));
                    sv.setMaKhoaHoc(jsonObject.getString("maKhoaHoc"));
                    sv.setMaLop(jsonObject.getString("maLop"));
                    sv.setNgaySinhView(jsonObject.getString("ngaySinh"));
                    sv.setSDT(jsonObject.getString("sdt"));
                    sv.setDiaChi(jsonObject.getString("diaChi"));
                    sv.setQueQuan(jsonObject.getString("queQuan"));
                    sv.setGioiTinh(jsonObject.getString("gioiTinh"));
                    arrSinhVien.add(sv);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println("Output from Server .... \n");
//            System.out.println(output);
            //phan tich chuoi Json day vao arraylist va day arraylist vao table
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return arrSinhVien;
    }

}
