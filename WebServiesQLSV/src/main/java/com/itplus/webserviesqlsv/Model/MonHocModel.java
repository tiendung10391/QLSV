/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.Model;

import com.itplus.webserviesqlsv.Entity.MonHocEntity;
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
public class MonHocModel {

    public MonHocModel() {
        DBPool db = new DBPool();
    }

    public ArrayList<MonHocEntity> getMonhoc() throws Exception {
        ArrayList<MonHocEntity> arr = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
//            DBPool.build(2);
            cn = DBPool.getConnection();
//            cn = DBUtil.connectSQL();
            stmt = cn.createStatement();
            String SQL = "SELECT * from MONHOC";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                MonHocEntity mon = new MonHocEntity();
                mon.setMaMH(rs.getString(1));
                mon.setTenMH(rs.getString(2));
                mon.setSoGio(Integer.parseInt(rs.getString(3)));
                mon.setMaKhoaHoc(rs.getString(4));
                arr.add(mon);
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

    public int addMonhoc(MonHocEntity mon) throws SQLException {
        int id = 0;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "insert into MONHOC values(?,?,?,?)";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
//            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, mon.getMaMH());
            stmt.setString(2, mon.getTenMH());
            stmt.setInt(3, mon.getSoGio());
            stmt.setString(4, mon.getMaKhoaHoc());
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

    public void editMonhoc(MonHocEntity mon) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "update MonHoc set TenMonHoc = ?, SoGio = ? where MaMonHoc = ? AND MaKhoaHoc = ? ";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, mon.getTenMH());
            stmt.setInt(2, mon.getSoGio());
            stmt.setString(3, mon.getMaMH());
            stmt.setString(4, mon.getMaKhoaHoc());
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

    public void deleteMonhoc(String MaMonHoc, String MaKhoaHoc) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "delete MONHOC  where MaMonHoc = ? AND MaKhoaHoc = ? ";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaMonHoc);
            stmt.setString(2, MaKhoaHoc);
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

    public void deleteMonhoc(ArrayList<MonHocEntity> arr) throws SQLException, Exception {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBPool.getConnection();
            conn.setAutoCommit(false);//tao transaction
            for (MonHocEntity mon : arr) {
                String SQL = "delete MonHoc  where  MaMonHoc = ? ";
                stmt = conn.prepareStatement(SQL);
                stmt.setString(1, mon.getMaMH());
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

//    public ArrayList<MonHocEntity> findByName(String tenMH) throws Exception {
//        ArrayList<MonHocEntity> arr = new ArrayList<MonHocEntity>();
//        PreparedStatement pstm = null;
//        ResultSet rs = null;
//        Connection cn = null;
//        try {
////            DBPool.build(2);
//            cn = DBPool.getConnection();
////            cn = DBUtil.connectSQL();
//            String SQL = "SELECT * from MonHoc where TenMonHoc like ?";
//            pstm = cn.prepareStatement(SQL);
//            pstm.setString(1, "%" + tenMH + "%");
//            rs = pstm.executeQuery(SQL);
//
//            while (rs.next()) {
//                MonHocEntity mon = new MonHocEntity();
//                mon.setMaMH(rs.getString(1));
//                mon.setTenMH(rs.getString(2));
//                mon.setSoGio(rs.getString(3));
//                arr.add(mon);
//            }
//        } catch (Exception ex) {
//            throw ex;
//        } finally {
//            try {
//                DBPool.releaseConnection(cn, pstm, rs);
//            } catch (Exception e) {
//                throw e;
//            }
//        }
//        return arr;
//    }
}
