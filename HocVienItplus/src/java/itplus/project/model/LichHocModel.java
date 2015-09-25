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
import itplus.project.entity.LichHocEntity;
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
public class LichHocModel implements Serializable{
    String MaLop = "";
    ArrayList<LichHocEntity> arrLichHoc = null;
    // lay ve malop tu ma sinh vien
    public String getMaLop(String MaSV) {
        
        try {
            //test call restful webservices
            Client client = new Client();
            WebResource webResource = client.resource("http://localhost:8084/WebServiesQLSV/rest/SwAdLichHoc/getMaLop");
            MultivaluedMap queryParams = new MultivaluedMapImpl();
            queryParams.add("MaSV", MaSV);

            String s = webResource.queryParams(queryParams).get(String.class);
            System.out.println("url:" + s);
            try {
                JSONArray jsonArray = new JSONArray(s);

                JSONObject jsonObj = new JSONObject();  // these 4 files add jsonObject to jsonArrayF
             
                int count = jsonArray.length(); // get totalCount of all jsonObjects
                for (int i = 0; i < count; i++) {   // iterate through jsonArray 
                    JSONObject jsonObject = jsonArray.getJSONObject(i);  // get jsonObject @ i position 
                    MaLop = jsonObject.getString("maLop");
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

        return MaLop;
    }
    
    // lay ve danh sach thoi khoa bieu theo ma lop
    public ArrayList<LichHocEntity> getAllLichHoc(String MaLop) {

        try {
            //test call restful webservices
            Client client = new Client();
            WebResource webResource = client.resource("http://localhost:8084/WebServiesQLSV/rest/SwAdLichHoc/getAllLichHoc_Ad");
            MultivaluedMap queryParams = new MultivaluedMapImpl();
            queryParams.add("MaLop", MaLop);

            String s = webResource.queryParams(queryParams).get(String.class);
            System.out.println("url:" + s);
            try {
                JSONArray jsonArray = new JSONArray(s);

                JSONObject jsonObj = new JSONObject();  // these 4 files add jsonObject to jsonArrayF
                arrLichHoc = new ArrayList<LichHocEntity>();
                int count = jsonArray.length(); // get totalCount of all jsonObjects
                for (int i = 0; i < count; i++) {   // iterate through jsonArray 
                    LichHocEntity lichHoc = new LichHocEntity();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);  // get jsonObject @ i position 
                    lichHoc.setGioHoc(jsonObject.getString("gioHoc"));
                    lichHoc.setMonHoc(jsonObject.getString("monHoc"));
                    lichHoc.setNgayHoc(jsonObject.getString("ngay"));
                    lichHoc.setPhongHoc(jsonObject.getString("phong"));
                    arrLichHoc.add(lichHoc);
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

        return arrLichHoc;
    }
    
}
