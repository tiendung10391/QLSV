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
            String SQL = "SELECT * from LOPHOC";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
               LopHocEntity lop = new LopHocEntity();
               lop.setMaLop(rs.getString(1));
               lop.setTenLop(rs.getString(2));
               lop.setHeDaoTao(rs.getString(3));
               lop.setNamNhapHoc(rs.getString(4));
               lop.setMaNganh(rs.getString(5));
               lop.setMaKhoaHoc(rs.getString(6));
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
     public int addLophoc(LopHocEntity lop) throws SQLException {
        int id = 0;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "insert into LOPHOC values(?,?,?,?,?,?)";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
//            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, lop.getMaLop());
            stmt.setString(2, lop.getTenLop());
            stmt.setString(3, lop.getHeDaoTao());
            stmt.setString(4, lop.getNamNhapHoc());
            stmt.setString(5, lop.getMaNganh());
            stmt.setString(6, lop.getMaKhoaHoc());
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

    public void editLophoc(LopHocEntity lop) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "update LOPHOC set TenLop = ?, HeDaoTao = ?, NamNhapHoc = ?, MaNganh = ?, MaKhoaHoc = ? where MaLop = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, lop.getTenLop());
            stmt.setString(2, lop.getHeDaoTao());
            stmt.setString(3, lop.getNamNhapHoc());
            stmt.setString(4, lop.getMaNganh());
            stmt.setString(5, lop.getMaKhoaHoc());
            stmt.setString(6, lop.getMaLop());
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

    public void deleteLophoc(String MaLop) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "delete LopHoc  where MaLop = ? ";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaLop);
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

    public void deleteLop(ArrayList<LopHocEntity> arr) throws SQLException, Exception {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBPool.getConnection();
            conn.setAutoCommit(false);//tao transaction
            for (LopHocEntity lop : arr) {
                String SQL = "delete LopHoc  where MaLop = ? ";
                stmt = conn.prepareStatement(SQL);
                stmt.setString(1, lop.getMaLop());
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

    public ArrayList<LopHocEntity> findByName(String MaLop) throws Exception {
        ArrayList<LopHocEntity> arr = new ArrayList<LopHocEntity>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
//            DBPool.build(2);
            cn = DBPool.getConnection();
//            cn = DBUtil.connectSQL();
            String SQL = "SELECT * from LopHoc where MaLop like ?";
            pstm = cn.prepareStatement(SQL);
            pstm.setString(1, "%" + MaLop + "%");
            rs = pstm.executeQuery(SQL);

            while (rs.next()) {
                LopHocEntity lop = new LopHocEntity();
                lop.setMaLop(rs.getString(1));
                lop.setTenLop(rs.getString(2));
                lop.setHeDaoTao(rs.getString(3));
                lop.setNamNhapHoc(rs.getString(4));
                lop.setMaNganh(rs.getString(5));
                lop.setMaKhoaHoc(rs.getString(6));
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
