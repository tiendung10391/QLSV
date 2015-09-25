/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.model;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import itplus.project.entity.ChuongTrinhEntity;
import itplus.project.entity.DiemThiEntity;
import itplus.project.entity.HocKyEntity;
import itplus.project.entity.KhoaHocEntity;
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
public class ChuongTrinhModel implements Serializable {

    ArrayList<ChuongTrinhEntity> arrChuongTrinh = null;
    ArrayList<HocKyEntity> arrHocKy = null;
    ArrayList<KhoaHocEntity> arrKhoaHoc = null;

    // lay ve danh sach chuong trinh hoc theo ma sinh vien
    public ArrayList<ChuongTrinhEntity> getChuongTrinhFormMaSV(String MaSV) {

        try {
            //test call restful webservices
            Client client = new Client();
            WebResource webResource = client.resource("http://localhost:8084/WebServiesQLSV/rest/SwAdChuongTrinh/getChuongTrinhFromMaSV");
            MultivaluedMap queryParams = new MultivaluedMapImpl();
            queryParams.add("MaSV", MaSV);

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
                arrChuongTrinh = new ArrayList<ChuongTrinhEntity>();
                int count = jsonArray.length(); // get totalCount of all jsonObjects
                for (int i = 0; i < count; i++) {   // iterate through jsonArray 
                    ChuongTrinhEntity chuongTrinh = new ChuongTrinhEntity();
                    DiemThiEntity diemThi = new DiemThiEntity();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);  // get jsonObject @ i position 
                    chuongTrinh.setMaMon(jsonObject.getString("maMon"));
                    chuongTrinh.setTenMon(jsonObject.getString("tenMon"));
                    chuongTrinh.setMaHocKy(jsonObject.getString("maHocKy"));
                    chuongTrinh.setTenHocKy(jsonObject.getString("tenHocKy"));
                    chuongTrinh.setMaKhoaHoc(jsonObject.getString("maKhoaHoc"));
                    chuongTrinh.setSoGio(Integer.parseInt(jsonObject.getString("soGio")));
                    chuongTrinh.setGhiChu(jsonObject.getString("ghiChu"));

                    arrChuongTrinh.add(chuongTrinh);
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

        return arrChuongTrinh;
    }

    // lay ve chuong trinh hoc qua ma khoa hoc
    public ArrayList<ChuongTrinhEntity> getChuongTrinhFormMaKhoaHoc(String MaKhoaHoc) {

        try {
            //test call restful webservices
            Client client = new Client();
            WebResource webResource = client.resource("http://localhost:8084/WebServiesQLSV/rest/SwAdChuongTrinh/getChuongTrinhFromMaKhoaHoc");
            MultivaluedMap queryParams = new MultivaluedMapImpl();
            queryParams.add("MaKhoaHoc", MaKhoaHoc);

            String s = webResource.queryParams(queryParams).get(String.class);
            System.out.println("url:" + s);
            try {
                JSONArray jsonArray = new JSONArray(s);

                JSONObject jsonObj = new JSONObject();  // these 4 files add jsonObject to jsonArrayF
                arrChuongTrinh = new ArrayList<ChuongTrinhEntity>();
                int count = jsonArray.length(); // get totalCount of all jsonObjects
                for (int i = 0; i < count; i++) {   // iterate through jsonArray 
                    ChuongTrinhEntity chuongTrinh = new ChuongTrinhEntity();
                    DiemThiEntity diemThi = new DiemThiEntity();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);  // get jsonObject @ i position 
                    chuongTrinh.setMaMon(jsonObject.getString("maMon"));
                    chuongTrinh.setTenMon(jsonObject.getString("tenMon"));
                    chuongTrinh.setMaHocKy(jsonObject.getString("maHocKy"));
                    chuongTrinh.setTenHocKy(jsonObject.getString("tenHocKy"));
                    chuongTrinh.setMaKhoaHoc(jsonObject.getString("maKhoaHoc"));
                    chuongTrinh.setSoGio(Integer.parseInt(jsonObject.getString("soGio")));
                    chuongTrinh.setGhiChu(jsonObject.getString("ghiChu"));

                    arrChuongTrinh.add(chuongTrinh);
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

        return arrChuongTrinh;
    }

    // lay ve danh sach cac hoc ky tu ma khoa hoc
    public ArrayList<HocKyEntity> getAllHocKyFormMaKhoaHoc(String MaKhoaHoc) {

        try {
            //test call restful webservices
            Client client = new Client();
            WebResource webResource = client.resource("http://localhost:8084/WebServiesQLSV/rest/SwAdChuongTrinh/getHocKyFromMaKhoaHoc");
            MultivaluedMap queryParams = new MultivaluedMapImpl();
            queryParams.add("MaKhoaHoc", MaKhoaHoc);

            String s = webResource.queryParams(queryParams).get(String.class);
            System.out.println("url:" + s);
            try {
                JSONArray jsonArray = new JSONArray(s);

                JSONObject jsonObj = new JSONObject();  // these 4 files add jsonObject to jsonArrayF
                arrHocKy = new ArrayList<HocKyEntity>();
                int count = jsonArray.length(); // get totalCount of all jsonObjects
                for (int i = 0; i < count; i++) {   // iterate through jsonArray 
                    HocKyEntity hk = new HocKyEntity();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);  // get jsonObject @ i position 
                    hk.setMaHocKy(jsonObject.getString("maHocKy"));
                    hk.setTenHocKy(jsonObject.getString("tenHocKy"));

                    arrHocKy.add(hk);
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

        return arrHocKy;
    }

    // lay ve danh sach khoa hoc
    public ArrayList<KhoaHocEntity> getAllKhoaHoc() {

        try {
            //test call restful webservices
            Client client = new Client();
            WebResource ws = client.resource("http://localhost:8084/WebServiesQLSV/rest/SwAdChuongTrinh/getAllKhoaHoc");
            ClientResponse response = ws.accept("application/json").get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);

            try {
                JSONArray jsonArray = new JSONArray(output);

                JSONObject jsonObj = new JSONObject();  // these 4 files add jsonObject to jsonArrayF
                arrKhoaHoc = new ArrayList<KhoaHocEntity>();
                int count = jsonArray.length(); // get totalCount of all jsonObjects
                for (int i = 0; i < count; i++) {   // iterate through jsonArray 
                    KhoaHocEntity kh = new KhoaHocEntity();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);  // get jsonObject @ i position 
                    System.out.println("jsonArray " + i + " : " + jsonObject.getString("maKhoaHoc"));
                    System.out.println("jsonArray " + i + " : " + jsonObject.getString("tenKhoaHoc"));
                    kh.setMaKhoaHoc(jsonObject.getString("maKhoaHoc"));
                    kh.setTenKhoaHoc(jsonObject.getString("tenKhoaHoc"));
                    arrKhoaHoc.add(kh);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            System.out.println("Output from Server .... \n");
            System.out.println(output);
            //phan tich chuoi Json day vao arraylist va day arraylist vao table
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return arrKhoaHoc;
    }

}
