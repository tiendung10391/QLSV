/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.Model;


import com.itplus.webserviesqlsv.Entity.LopHocEntity;
import com.itplus.webserviesqlsv.Pool.DBPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 *
 * @author kunph_000
 */
public class LopHocModel {

    public LopHocModel() {
         DBPool db = new DBPool();
    }
    public ArrayList<LopHocEntity> getLophoc() throws Exception {
        ArrayList<LopHocEntity> arr = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
//            DBPool.build(2);
            cn = DBPool.getConnection();
//            cn = DBUtil.connectSQL();
            stmt = cn.createStatement();
            String SQL = "SELECT MaLop,TenLop,MaKhoaHoc from LOPHOC";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
               LopHocEntity lop = new LopHocEntity();
               lop.setMaLop(rs.getString("MaLop"));
               lop.setTenLop(rs.getString("TenLop"));
               lop.setMaKhoaHoc(rs.getString("MaKhoaHoc"));
                arr.add(lop);
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
