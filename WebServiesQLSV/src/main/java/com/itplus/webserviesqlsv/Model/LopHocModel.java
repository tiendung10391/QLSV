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
        ArrayList<LopHocEntity> arr = new ArrayList<LopHocEntity>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
            cn = DBPool.getConnection();
            stmt = cn.createStatement();
            String SQL = "SELECT * from LOPHOC";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
               LopHocEntity lop = new LopHocEntity();
               lop.setMaLop(rs.getString(1));
               lop.setTenLop(rs.getString(2));
               lop.setHeDaoTao(rs.getString(3));
               lop.setNamNhapHoc(rs.getString(4));
               lop.setMaNganh(rs.getString(5));
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
    
    public int addLopHoc(LopHocEntity lopHoc) throws SQLException {
        int id = 0;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "INSERT INTO LOPHOC values(?,?,?,?,?,?)";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, lopHoc.getMaLop());
            stmt.setString(2, lopHoc.getTenLop());
            stmt.setString(3, lopHoc.getHeDaoTao());
            stmt.setString(4, lopHoc.getNamNhapHoc());
            stmt.setString(5, lopHoc.getMaNganh());
            stmt.setString(6, lopHoc.getMaKhoaHoc());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            System.out.println("ID: " + id);
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                throw ex;
            }
        }
        return id;
    }
    
}
