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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


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
      public int addGioHoc(GioHocEntity gio) throws SQLException {
        int id = 0;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "insert into diem values(?,?)";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
//            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, gio.getMaGioHoc());
            stmt.setString(2, gio.getThoiGian());
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
       public void editGioHoc(GioHocEntity gio) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "update giohoc set ThoiGian = ? where MaGioHoc = ?";
            conn = DBPool.getConnection();
            SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, gio.getThoiGian());
            stmt.setString(2, gio.getMaGioHoc());
            stmt.executeUpdate();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                throw ex;
            }
        }
    }
       public void deleteGioHoc(String Magiohoc) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "delete giohoc  where magiohoc = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, Magiohoc);
            stmt.executeUpdate();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                throw ex;
            }
        }
        
    }
        public void deleteGioHoc(ArrayList<GioHocEntity> arr) throws SQLException, Exception {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBPool.getConnection();
            conn.setAutoCommit(false);//tao transaction
            for (GioHocEntity gio : arr) {
                String SQL = "delete giohoc  where magiohoc = ?";
                stmt = conn.prepareStatement(SQL);
                stmt.setString(1, gio.getMaGioHoc());
                stmt.executeUpdate();
            }
            conn.commit();

        } catch (Exception ex) {
            conn.rollback();
            conn.setAutoCommit(true);
            throw new Exception(ex.getMessage());

        } finally {

            DBPool.releaseConnection(conn, stmt);
        }
    }
}
