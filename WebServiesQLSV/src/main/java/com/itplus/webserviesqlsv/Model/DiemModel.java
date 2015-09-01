/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.Model;


import com.itplus.webserviesqlsv.Entity.DiemEntity;
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
public class DiemModel {

    public DiemModel() {
         DBPool db = new DBPool();
    }
     public ArrayList<DiemEntity> getDiem() throws Exception {
        ArrayList<DiemEntity> arr = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
//            DBPool.build(2);
            cn = DBPool.getConnection();
//            cn = DBUtil.connectSQL();
            stmt = cn.createStatement();
            String SQL = "SELECT * from Diem";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                DiemEntity diem = new DiemEntity();
                diem.setMaMonHoc(rs.getString(1));
                diem.setMaSV(rs.getString(2));
                diem.setDiemLan1(rs.getInt(3));
                diem.setDiemlan2(rs.getInt(4));
                diem.setDiemLan3(rs.getInt(5));
                diem.setTrangThai(rs.getBoolean(6));
                arr.add(diem);
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
      public int addDiem(DiemEntity diem) throws SQLException {
        int id = 0;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "insert into diem values(?,?,?,?,?,?)";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
//            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, diem.getMaMonHoc());
            stmt.setString(2, diem.getMaSV());
            stmt.setInt(3, diem.getDiemLan1());
            stmt.setInt(4, diem.getDiemlan2());
            stmt.setInt(5, diem.getDiemLan3());
            stmt.setBoolean(6, diem.isTrangThai());
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
       public void editRegister(DiemEntity diem) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "update diem set MaMonHoc = ? where MaSV = ?";
            conn = DBPool.getConnection();
            SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, diem.getMaMonHoc());
            stmt.setString(2, diem.getMaSV());
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
       public void deleteDiem(String MaMH ,String MaSV) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "delete Diem  where mamonhoc = ? and masv = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaMH);
            stmt.setString(2, MaSV);
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
        public void deleteDiem(ArrayList<DiemEntity> arr) throws SQLException, Exception {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBPool.getConnection();
            conn.setAutoCommit(false);//tao transaction
            for (DiemEntity diem : arr) {
                String SQL = "delete diem  where MaMonHoc = ? and MaSV = ?";
                stmt = conn.prepareStatement(SQL);
                stmt.setString(1, diem.getMaMonHoc());
                stmt.setString(2, diem.getMaSV());
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
        //tim kiem thong tin theo ten
    public ArrayList<DiemEntity> findByName(String MaSV) throws Exception {
        ArrayList<DiemEntity> arr = new ArrayList<DiemEntity>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
//            DBPool.build(2);
            cn = DBPool.getConnection();
//            cn = DBUtil.connectSQL();
            String SQL = "SELECT * from Diem where MaSV like ?";
            pstm = cn.prepareStatement(SQL);
            pstm.setString(1, "%"+MaSV+"%");
            rs = pstm.executeQuery(SQL);

            while (rs.next()) {
               DiemEntity diem = new DiemEntity();
                 diem.setMaMonHoc(rs.getString(1));
                diem.setMaSV(rs.getString(2));
                diem.setDiemLan1(rs.getInt(3));
                diem.setDiemlan2(rs.getInt(4));
                diem.setDiemLan3(rs.getInt(5));
                diem.setTrangThai(rs.getBoolean(6));
                arr.add(diem);
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
