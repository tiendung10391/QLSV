/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.model;

import itplus.project.entity.KhoaHocEntity;
import itplus.project.entity.LopHocEntity;
import itplus.project.entity.MonHocEntity;
import itplus.project.pool.DBPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Dung NT
 */
public class MonHocModel {

    public MonHocModel() {
        DBPool db = new DBPool();
    }

    public ArrayList<MonHocEntity> getAllMonHoc() throws Exception {
        ArrayList<MonHocEntity> arrMonHoc = new ArrayList<MonHocEntity>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
            cn = DBPool.getConnection();
            stmt = cn.createStatement();
            String SQL = "SELECT * FROM MONHOC";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                MonHocEntity monHoc = new MonHocEntity();
                monHoc.setMaMonHoc(rs.getString(1));
                monHoc.setTenMonHoc(rs.getString(2));
                monHoc.setSoGio(rs.getString(3));
                monHoc.setGhiChu(rs.getString(4));
                arrMonHoc.add(monHoc);
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

        return arrMonHoc;
    }
    
    public int addMonHoc(MonHocEntity monHoc) throws SQLException {
        int id = 0;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {

            String SQL = "insert into MONHOC values(?,?,?,?)";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
//            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, monHoc.getMaMonHoc());
            stmt.setString(2, monHoc.getTenMonHoc());
            stmt.setString(3, monHoc.getSoGio());
            stmt.setString(4, monHoc.getGhiChu());
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

    public void editMonHoc(MonHocEntity monHoc) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "UPDATE MONHOC set TenMonHoc = ?, SoGio = ?, GhiChu = ? where MaMonHoc = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, monHoc.getTenMonHoc());
            stmt.setString(2, monHoc.getSoGio());
            stmt.setString(3, monHoc.getGhiChu());
            stmt.setString(4, monHoc.getMaMonHoc());
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
    
    public void deleteMonHoc(ArrayList<MonHocEntity> arr) throws SQLException, Exception {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBPool.getConnection();
            conn.setAutoCommit(false);//tao transaction
            for (MonHocEntity monHoc : arr) {
                String SQL = "DELETE MONHOC  where MaMonHoc = ? ";
                stmt = conn.prepareStatement(SQL);
                stmt.setString(1, monHoc.getMaMonHoc());
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
    
    public boolean checkDuplicateMaMonHoc(String MaMonHoc) throws Exception {

        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * from MONHOC WHERE MaMonHoc = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaMonHoc);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return true;
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
        return false;
    }
    
    public String getTenMonHoc(String MaMonHoc) throws Exception {
        String TenMonHoc = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT TenMonHoc from MONHOC WHERE MaMonHoc = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaMonHoc);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TenMonHoc = rs.getString(1);
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
        return TenMonHoc;
    }
    
    public ArrayList<MonHocEntity> getAllMonHocFormMaLop(String MaLop) throws Exception {
        ArrayList<MonHocEntity> arr = new ArrayList<MonHocEntity>();
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * FROM VIEW_DANHSACHMONHOC_LOPHOC WHERE MaLop = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaLop);
            rs = stmt.executeQuery();
            while (rs.next()) {
                MonHocEntity monHoc = new MonHocEntity();
                monHoc.setMaMonHoc(rs.getString(4));
                monHoc.setTenMonHoc(rs.getString(5));
                
                arr.add(monHoc);
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
    
}
