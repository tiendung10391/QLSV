/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.model;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import itplus.project.entity.DiemThiEntity;
import itplus.project.entity.HocKyEntity;
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
public class DiemThiModel implements Serializable {

    ArrayList<DiemThiEntity> arrDiemKhoaHoc = null;
    ArrayList<DiemThiEntity> arrDiemHocKy = null;
    ArrayList<HocKyEntity> arrHocKy = null;

    // lay ve danh sach cac diem theo khoa hoc
    public ArrayList<DiemThiEntity> getDiemKhoaHoc(String MaSV) {

        try {
            //test call restful webservices
            Client client = new Client();
            WebResource webResource = client.resource("http://localhost:8084/WebServiesQLSV/rest/SwDiem/getAllDiemThiKhoaHoc");
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
                arrDiemKhoaHoc = new ArrayList<DiemThiEntity>();
                int count = jsonArray.length(); // get totalCount of all jsonObjects
                for (int i = 0; i < count; i++) {   // iterate through jsonArray 
                    DiemThiEntity diemThi = new DiemThiEntity();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);  // get jsonObject @ i position 
                    diemThi.setId_diem(Integer.parseInt(jsonObject.getString("id_diem")));
                    diemThi.setMaMon(jsonObject.getString("maMonHoc"));
                    diemThi.setTenMon(jsonObject.getString("tenMon"));
                    diemThi.setMaHocKy(jsonObject.getString("maHocKy"));
                    diemThi.setDiemLan1(Integer.parseInt(jsonObject.getString("diemLan1")));
                    diemThi.setDiemLan2(Integer.parseInt(jsonObject.getString("diemLan2")));
                    diemThi.setDiemLan3(Integer.parseInt(jsonObject.getString("diemLan3")));
                    int diem1 = Integer.parseInt(jsonObject.getString("diemLan1"));
                    int diem2 = Integer.parseInt(jsonObject.getString("diemLan2"));
                    int diem3 = Integer.parseInt(jsonObject.getString("diemLan3"));
                    diemThi.setTenHocKy(setTenHocKy(jsonObject.getString("maHocKy")));
                    diemThi.setTrangThai(setTrangThai(diem1, diem2, diem3));
                    arrDiemKhoaHoc.add(diemThi);
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

        return arrDiemKhoaHoc;
    }

    // lay ve danh sach diem hoc ky
    public ArrayList<DiemThiEntity> getDiemHocKy(String MaSV) {

        try {
            //test call restful webservices
            Client client = new Client();
            WebResource webResource = client.resource("http://localhost:8084/WebServiesQLSV/rest/SwDiem/getAllDiemThiTheoKy");
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
                arrDiemHocKy = new ArrayList<DiemThiEntity>();
                int count = jsonArray.length(); // get totalCount of all jsonObjects
                for (int i = 0; i < count; i++) {   // iterate through jsonArray 
                    DiemThiEntity diemThi = new DiemThiEntity();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);  // get jsonObject @ i position 
                    diemThi.setId_diem(Integer.parseInt(jsonObject.getString("id_diem")));
                    diemThi.setMaMon(jsonObject.getString("maMonHoc"));
                    diemThi.setTenMon(jsonObject.getString("tenMon"));
                    diemThi.setMaHocKy(jsonObject.getString("maHocKy"));
                    diemThi.setDiemLan1(Integer.parseInt(jsonObject.getString("diemLan1")));
                    diemThi.setDiemLan2(Integer.parseInt(jsonObject.getString("diemLan2")));
                    diemThi.setDiemLan3(Integer.parseInt(jsonObject.getString("diemLan3")));
                    int diem1 = Integer.parseInt(jsonObject.getString("diemLan1"));
                    int diem2 = Integer.parseInt(jsonObject.getString("diemLan2"));
                    int diem3 = Integer.parseInt(jsonObject.getString("diemLan3"));
                    System.out.println("trang thai: " + setTrangThai(diem1, diem2, diem3));
                    diemThi.setTrangThai(setTrangThai(diem1, diem2, diem3));
                    arrDiemHocKy.add(diemThi);
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

        return arrDiemHocKy;
    }
    
    // lay ve danh sach hoc ky cua sinh vien
    public ArrayList<HocKyEntity> getAllHocKy(String MaSV) {

        try {
            //test call restful webservices
            Client client = new Client();
            WebResource webResource = client.resource("http://localhost:8084/WebServiesQLSV/rest/SwHocKy/getAllHocKy");
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
                arrHocKy = new ArrayList<HocKyEntity>();
                int count = jsonArray.length(); // get totalCount of all jsonObjects
                for (int i = 0; i < count; i++) {   // iterate through jsonArray 
                    HocKyEntity hocKy = new HocKyEntity();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);  // get jsonObject @ i position 
                    hocKy.setMaHocKy(jsonObject.getString("maHocKy"));
                    hocKy.setTenHocKy(jsonObject.getString("tenHocKy"));
                    arrHocKy.add(hocKy);
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

    public String setTrangThai(int diem1, int diem2, int diem3) {
        if (diem1 >= 65) {
            return "đạt";
        } else {
            if (diem2 >= 65) {
                return "đạt";
            } else {
                if (diem3 >= 65) {
                    return "đạt";
                } else {
                    return "không đạt";
                }
            }
        }

    }
    
    public String setTenHocKy(String MaHK){
        if(MaHK.equals("HK1")){
            return "Học Kỳ 1";
        }else if(MaHK.equals("HK2")){
            return "Học Kỳ 2";
        }else if(MaHK.equals("HK3")){
            return "Học Kỳ 3";
        }else if(MaHK.equals("HK4")){
            return "Học Kỳ 4";
        }
                return "Học Kỳ 5";
    }
}
