/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.Model;

import com.itplus.webserviesqlsv.Entity.KhoaHocEntity;
import com.itplus.webserviesqlsv.Pool.DBPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Dung NT
 */
public class KhoaHocModel {

    public KhoaHocModel() {
        DBPool db = new DBPool();
    }
    
     public ArrayList<KhoaHocEntity> getAllKhoaHoc() throws Exception {
        ArrayList<KhoaHocEntity> arr = new ArrayList<KhoaHocEntity>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
            cn = DBPool.getConnection();
            stmt = cn.createStatement();
            String SQL = "SELECT * from KHOAHOC";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                KhoaHocEntity khoaHoc = new KhoaHocEntity();
                khoaHoc.setMaKhoaHoc(rs.getString(1));
                khoaHoc.setTenKhoaHoc(rs.getString(2));
                
                arr.add(khoaHoc);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                DBPool.releaseConnection(cn, stmt, rs);
            } catch (Exception e) {
                throw e;
            }
        }
        return arr;
    }
     
     
     
     
     
     

}
