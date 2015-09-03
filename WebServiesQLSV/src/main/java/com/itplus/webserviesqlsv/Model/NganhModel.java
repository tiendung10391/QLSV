/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.Model;

import com.itplus.webserviesqlsv.Entity.LopHocEntity;
import com.itplus.webserviesqlsv.Entity.NganhEntity;
import com.itplus.webserviesqlsv.Pool.DBPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Dung NT
 */
public class NganhModel {

    public NganhModel() {
        DBPool db = new DBPool();

    }

    public ArrayList<NganhEntity> getAllNganh() throws Exception {
        ArrayList<NganhEntity> arr = new ArrayList<NganhEntity>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
            cn = DBPool.getConnection();
            stmt = cn.createStatement();
            String SQL = "SELECT * from NGANH";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                NganhEntity nganh = new NganhEntity();
                nganh.setMaNganh(rs.getString(1));
                nganh.setTenNganh(rs.getString(2));
                arr.add(nganh);
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
