/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.Model;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.itplus.webserviesqlsv.Entity.GioHocEntity;
import com.itplus.webserviesqlsv.Pool.DBPool;


/**
 *
 * @author kunph_000
 */
public class GioHocModel {

    public GioHocModel() {
        DBPool db = new DBPool();
    }
     public ArrayList<GioHocEntity> getGioHoc() throws Exception {
        ArrayList<GioHocEntity> arr = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
//            DBPool.build(2);
            cn = DBPool.getConnection();
//            cn = DBUtil.connectSQL();
            stmt = cn.createStatement();
            String SQL = "SELECT * from GioHoc";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                GioHocEntity gio = new GioHocEntity();
                gio.setMaGioHoc(rs.getString(1));
                gio.setThoiGian(rs.getString(2));
                arr.add(gio);
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
