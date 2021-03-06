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

    public ArrayList<SinhVienEntity> getSinhVien() throws Exception {
        ArrayList<SinhVienEntity> arr = new ArrayList<SinhVienEntity>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
            cn = DBPool.getConnection();
            stmt = cn.createStatement();
            String SQL = "SELECT * from SINHVIEN ";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                SinhVienEntity sv = new SinhVienEntity();
                sv.setMaSV(rs.getString(1));
                sv.setTenSV(rs.getString(2));
                sv.setNgaySinh(rs.getString(3));
                sv.setGioiTinh(rs.getString(4));
                sv.setSdt(rs.getString(5));
                sv.setDiaChi(rs.getString(6));
                sv.setQueQuan(rs.getString(7));
                sv.setEmail(rs.getString(8));
                sv.setMaLop(rs.getString(9));
                sv.setMatKhau(rs.getString(10));
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
            stmt.setString(4, sv.getGioiTinh());
            stmt.setString(5, sv.getSdt());
            stmt.setString(6, sv.getDiaChi());
            stmt.setString(7, sv.getQueQuan());
            stmt.setString(8, sv.getEmail());
            stmt.setString(9, sv.getMaLop());
            stmt.setString(10, sv.getMatKhau());
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

    public boolean editSinhVien(String matkhau, String masv) throws SQLException {
        boolean updateStatus = false;
        PreparedStatement stmt = null;
        Connection conn = null;
       int count;
        try {
            String SQL = "UPDATE SINHVIEN SET MatKhau = ? WHERE MaSV = ?";
            conn = DBPool.getConnection(); 
             stmt= conn.prepareStatement(SQL);
             stmt.setString(1, matkhau);           
             stmt.setString(2,masv);
            count= stmt.executeUpdate();
            if (count>0) {
                updateStatus = true;
            }
             

        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                throw ex;
            }
        }
        return updateStatus;
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

    public ArrayList<SinhVienEntity> getInfoSinhVien(String MaSV) throws Exception {
        ArrayList<SinhVienEntity> arr = new ArrayList<SinhVienEntity>();
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * FROM VIEW_INFO_SINHVIEN WHERE MaSV = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaSV);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            rs = stmt.executeQuery();

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
                DBPool.releaseConnection(conn, stmt, rs);
            } catch (Exception e) {
                throw e;
            }
        }
        return arr;
    }
}
