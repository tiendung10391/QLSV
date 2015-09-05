/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.Model;

import com.itplus.webserviesqlsv.Entity.NganhEntity;
import com.itplus.webserviesqlsv.Entity.PhongHocEntity;
import com.itplus.webserviesqlsv.Pool.DBPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Dung NT
 */
public class PhongHocModel {
    
    public PhongHocModel(){
        DBPool db = new DBPool();
    }
    
    public ArrayList<PhongHocEntity> getAllPhongHoc() throws Exception {
        ArrayList<PhongHocEntity> arr = new ArrayList<PhongHocEntity>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
            cn = DBPool.getConnection();
            stmt = cn.createStatement();
            String SQL = "SELECT * from PHONGHOC";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                PhongHocEntity phongHoc = new PhongHocEntity();
                phongHoc.setMaPhongHoc(rs.getString(1));
                phongHoc.setTenPhongHoc(rs.getString(2));
                arr.add(phongHoc);
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
