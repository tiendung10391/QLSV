/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.Model;

import com.itplus.webserviesqlsv.Entity.LopMonHocEntity;
import com.itplus.webserviesqlsv.Entity.SinhVienEntity;
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
 * @author Dung NT
 */
public class SinhVienModel {

    public SinhVienModel() {
        DBPool db = new DBPool();
    }

    public ArrayList<SinhVienEntity> getSinhVien() throws Exception {
        ArrayList<SinhVienEntity> arr = new ArrayList<SinhVienEntity>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
            cn = DBPool.getConnection();
            stmt = cn.createStatement();
            String SQL = "SELECT * from SINHVIEN";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                SinhVienEntity sv = new SinhVienEntity();
                sv.setMaLop(rs.getString(1));
                sv.setTenSV(rs.getString(2));
                sv.setNgaySinh(rs.getString(3));
                sv.setGioiTinh(rs.getBoolean(4));
                sv.setSdt(rs.getString(5));
                sv.setDiaChi(rs.getString(6));
                sv.setQueQuan(rs.getString(7));
                sv.setEmail(rs.getString(8));
                sv.setMaLop(rs.getString(9));
                arr.add(sv);
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

    public int addSinhVien(SinhVienEntity sv) throws SQLException {
        int id = 0;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "insert into SINHVIEN values(?,?,?,?,?,?,?,?,?)";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, sv.getMaSV());
            stmt.setString(2, sv.getTenSV());
            stmt.setString(3, sv.getNgaySinh());
            stmt.setBoolean(4, sv.getGioiTinh());
            stmt.setString(5, sv.getSdt());
            stmt.setString(6, sv.getDiaChi());
            stmt.setString(7, sv.getQueQuan());
            stmt.setString(8, sv.getEmail());
            stmt.setString(9, sv.getMaLop());
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

    public void editSinhVien(SinhVienEntity sv) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "update SINHVIEN set TenSV = ?, NgaySinh = ?, GioiTinh = ?, SDT = ?, DiaChi = ?, QueQuan = ?, Email = ?, MaLop = ? WHERE MaSV = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, sv.getTenSV());
            stmt.setString(2, sv.getNgaySinh());
            stmt.setBoolean(3, sv.getGioiTinh());
            stmt.setString(4, sv.getSdt());
            stmt.setString(5, sv.getDiaChi());
            stmt.setString(6, sv.getQueQuan());
            stmt.setString(7, sv.getEmail());
            stmt.setString(8, sv.getMaLop());
            stmt.setString(9, sv.getMaSV());
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
    
    public void deleteSinhVien(String MaSV) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "delete SINHVIEN  where MaSV = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaSV);
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
}
