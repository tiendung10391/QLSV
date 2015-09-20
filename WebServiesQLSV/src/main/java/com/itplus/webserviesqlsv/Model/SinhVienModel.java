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
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Dung NT
 */
public class SinhVienModel {

    public SinhVienModel() {
        DBPool db = new DBPool();
    }

    // check username va password trong csdl
    public boolean checkLogin(String username, String password) throws Exception {
        boolean isUserAvailable = false;
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * FROM SINHVIEN WHERE MaSV = '" + username + "' AND MatKhau = '" + password + "'";
            conn = DBPool.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                isUserAvailable = true;
            }
        } catch (Exception ex) {
            throw new Exception();
        } finally {
            try {
                DBPool.releaseConnection(conn, stmt, rs);
            } catch (Exception ex) {
                throw new Exception();
            }
        }

        return isUserAvailable;
    }

    public String checkSinhVien(String masv) throws Exception {
        JSONObject obj = new JSONObject();
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * FROM SINHVIEN WHERE MaSV = '" + masv + "' ";
            conn = DBPool.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                obj.put("MaSV", rs.getString(1));
                obj.put("TenSV", rs.getString(2));
                obj.put("NgaySinh", rs.getString(3));
                obj.put("GioiTinh", rs.getBoolean(4));
                obj.put("Sdt", rs.getString(5));
                obj.put("DiaChi", rs.getString(6));
                obj.put("QueQuan", rs.getString(7));
                obj.put("Email", rs.getString(8));
                obj.put("MaLop", rs.getString(9));
            }
        } catch (Exception ex) {
            throw new Exception();
        } finally {
            try {
                DBPool.releaseConnection(conn, stmt, rs);
            } catch (Exception ex) {
                throw new Exception();
            }
        }

        return obj.toString();
    }


    public ArrayList<SinhVienEntity> getLoginSinhVien() throws Exception {
        ArrayList<SinhVienEntity> arr = new ArrayList<SinhVienEntity>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
            cn = DBPool.getConnection();
            stmt = cn.createStatement();
            String SQL = "SELECT MaSV,MatKhau from SINHVIEN";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                SinhVienEntity sv = new SinhVienEntity();
                sv.setMaSV(rs.getString(1));
                sv.setMatKhau(rs.getString(2));
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

    public ArrayList<SinhVienEntity> getInfoSinhVien() throws Exception {
        ArrayList<SinhVienEntity> arr = new ArrayList<SinhVienEntity>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
            cn = DBPool.getConnection();
            stmt = cn.createStatement();
            String SQL = "SELECT * FROM VIEW_INFO_SINHVIEN";
            rs = stmt.executeQuery(SQL);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            while (rs.next()) {
                SinhVienEntity sv = new SinhVienEntity();
                sv.setMaSV(rs.getString(1));
                sv.setTenSV(rs.getString(2));
                sv.setNgaySinh(format.format(rs.getDate(3)));
                sv.setGioiTinh(rs.getString(4));
                sv.setSdt(rs.getString(5));
                sv.setDiaChi(rs.getString(6));
                sv.setQueQuan(rs.getString(7));
                sv.setEmail(rs.getString(8));
                sv.setMaLop(rs.getString(9));
                sv.setTenLop(rs.getString(10));
                sv.setNamNhapHoc(rs.getString(11));
                sv.setMaKhoaHoc(rs.getString(12));
                sv.setTenKhoaHoc(rs.getString(13));
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
