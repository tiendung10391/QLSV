/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.Model;


import com.itplus.webserviesqlsv.Entity.LopMonHocEntity;
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
public class LopMonHocModel {
     public LopMonHocModel() {
         DBPool db = new DBPool();
    }
     public ArrayList<LopMonHocEntity> getLophoc() throws Exception {
        ArrayList<LopMonHocEntity> arr = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
//            DBPool.build(2);
            cn = DBPool.getConnection();
//            cn = DBUtil.connectSQL();
            stmt = cn.createStatement();
            String SQL = "SELECT * from LopMonHoc";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                LopMonHocEntity lop = new LopMonHocEntity();
                lop.setMaMH(rs.getString(1));
                lop.setMaLop(rs.getString(2));
                lop.setMaGiohoc(rs.getString(3));
                lop.setTenPhong(rs.getString(4));
                lop.setMaHocky(rs.getString(5));
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
      public int addLopmonhoc(LopMonHocEntity lop) throws SQLException {
        int id = 0;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "insert into LopMonHoc values(?,?,?,?,?)";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
//            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, lop.getMaMH());
            stmt.setString(2, lop.getMaLop());
            stmt.setString(3, lop.getMaGiohoc());
            stmt.setString(4, lop.getTenPhong());
            stmt.setString(5, lop.getMaHocky());
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
       public void editLopmonhoc(LopMonHocEntity lop) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "update lopmonhoc set TenPhong = ? where MaMonHoc = ? and MaLop = ? and MaGioHoc = ? ";
            conn = DBPool.getConnection();
            SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, lop.getTenPhong());
            stmt.setString(2, lop.getMaMH());
            stmt.setString(3, lop.getMaLop());
            stmt.setString(4, lop.getMaGiohoc());
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
        public void deleteLopmonhoc(String maMH, String maLop , String maGiohoc) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "delete LopMonHoc  where MaMonHoc = ? and MaLop = ? and MaGioHoc = ? ";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, maMH);
            stmt.setString(2, maLop);
            stmt.setString(3, maGiohoc);
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
         public void deleteLopmonhoc(ArrayList<LopMonHocEntity> arr) throws SQLException, Exception {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBPool.getConnection();
            conn.setAutoCommit(false);//tao transaction
            for (LopMonHocEntity lop : arr) {
                String SQL = "delete LopMonHoc  where  MaMonHoc = ? and MaLop = ? and MaGioHoc = ? ";
                stmt = conn.prepareStatement(SQL);
                stmt.setString(1, lop.getMaMH());
                stmt.setString(2, lop.getMaLop());
                stmt.setString(1, lop.getMaGiohoc());
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

    public ArrayList<LopMonHocEntity> findByName(String tenphong) throws Exception {
        ArrayList<LopMonHocEntity> arr = new ArrayList<LopMonHocEntity>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
//            DBPool.build(2);
            cn = DBPool.getConnection();
//            cn = DBUtil.connectSQL();
            String SQL = "SELECT * from LopMonHoc where TenPhong like ?";
            pstm = cn.prepareStatement(SQL);
            pstm.setString(1, "%" + tenphong + "%");
            rs = pstm.executeQuery(SQL);

            while (rs.next()) {
                LopMonHocEntity lop = new LopMonHocEntity();
                lop.setMaMH(rs.getString(1));
                lop.setMaLop(rs.getString(2));
                lop.setMaGiohoc(rs.getString(3));
                lop.setTenPhong(rs.getString(4));
                lop.setMaHocky(rs.getString(5));
                arr.add(lop);
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
