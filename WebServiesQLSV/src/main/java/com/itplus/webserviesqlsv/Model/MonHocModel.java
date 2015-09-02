/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.Model;

import com.itplus.webserviesqlsv.Entity.MonHoc;
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

    public ArrayList<MonHoc> getMonhoc() throws Exception {
        ArrayList<MonHoc> arr = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
//            DBPool.build(2);
            cn = DBPool.getConnection();
//            cn = DBUtil.connectSQL();
            stmt = cn.createStatement();
            String SQL = "SELECT * from MonHoc";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                MonHoc mon = new MonHoc();
                mon.setMaMH(rs.getString(1));
                mon.setTenMH(rs.getString(2));
                mon.setSoGio(rs.getString(3));
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

    public int addMonhoc(MonHoc mon) throws SQLException {
        int id = 0;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "insert into MonHoc values(?,?,?)";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
//            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, mon.getMaMH());
            stmt.setString(2, mon.getTenMH());
            stmt.setString(3, mon.getSoGio());
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

    public void editMonhoc(MonHoc mon) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "update MonHoc set TenMonHoc = ? where MaMonHoc = ?  ";
            conn = DBPool.getConnection();
            SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, mon.getTenMH());
            stmt.setString(2, mon.getMaMH());
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

    public void deleteMonhoc(String maMH) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "delete MonHoc  where MaMonHoc = ? ";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, maMH);
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

    public void deleteMonhoc(ArrayList<MonHoc> arr) throws SQLException, Exception {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBPool.getConnection();
            conn.setAutoCommit(false);//tao transaction
            for (MonHoc mon : arr) {
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

    public ArrayList<MonHoc> findByName(String tenMH) throws Exception {
        ArrayList<MonHoc> arr = new ArrayList<MonHoc>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
//            DBPool.build(2);
            cn = DBPool.getConnection();
//            cn = DBUtil.connectSQL();
            String SQL = "SELECT * from MonHoc where TenMonHoc like ?";
            pstm = cn.prepareStatement(SQL);
            pstm.setString(1, "%" + tenMH + "%");
            rs = pstm.executeQuery(SQL);

            while (rs.next()) {
                MonHoc mon = new MonHoc();
                mon.setMaMH(rs.getString(1));
                mon.setTenMH(rs.getString(2));
                mon.setSoGio(rs.getString(3));
                arr.add(mon);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                DBPool.releaseConnection(cn, pstm, rs);
            } catch (Exception e) {
                throw e;
            }
        }
        return arr;
    }
}
