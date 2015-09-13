/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.model;

import itplus.project.entity.KhoaHocEntity;
import itplus.project.pool.DBPool;
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
                khoaHoc.setHeDaoTao(rs.getString(3));
                khoaHoc.setMaNganh(rs.getString(4));
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
    
    public ArrayList<KhoaHocEntity> getInfoKhoaHocFormMaKhoaHoc(String MaKhoaHoc) throws Exception {
        ArrayList<KhoaHocEntity> arr = new ArrayList<KhoaHocEntity>();
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * from KHOAHOC WHERE MaKhoaHoc = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaKhoaHoc);
            rs = stmt.executeQuery();
            while (rs.next()) {
                KhoaHocEntity khoaHoc = new KhoaHocEntity();
                khoaHoc.setMaKhoaHoc(rs.getString(1));
                khoaHoc.setTenKhoaHoc(rs.getString(2));
                khoaHoc.setHeDaoTao(rs.getString(3));
                khoaHoc.setMaNganh(rs.getString(4));
                arr.add(khoaHoc);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                DBPool.releaseConnection(conn, stmt, rs);
            } catch (Exception e) {
                throw e;
            }
        }
        return arr;
    }

    public ArrayList<KhoaHocEntity> getKhoaHocFormMaNganh(String MaNganh) throws Exception {
        ArrayList<KhoaHocEntity> arr = new ArrayList<KhoaHocEntity>();
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * from KHOAHOC WHERE MaNganh = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaNganh);
            rs = stmt.executeQuery();
            while (rs.next()) {
                KhoaHocEntity khoaHoc = new KhoaHocEntity();
                khoaHoc.setMaKhoaHoc(rs.getString(1));
                khoaHoc.setTenKhoaHoc(rs.getString(2));
                khoaHoc.setHeDaoTao(rs.getString(3));
                arr.add(khoaHoc);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                DBPool.releaseConnection(conn, stmt, rs);
            } catch (Exception e) {
                throw e;
            }
        }
        return arr;
    }
    
    public String getTenKhoaHoc(String MaKhoaHoc) throws Exception {
        String TenKhoaHoc = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT TenKhoaHoc from KHOAHOC WHERE MaKhoaHoc = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaKhoaHoc);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TenKhoaHoc = rs.getString(1);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                DBPool.releaseConnection(conn, stmt, rs);
            } catch (Exception e) {
                throw e;
            }
        }
        return TenKhoaHoc;
    }

}
