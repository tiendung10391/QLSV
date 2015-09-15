/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.model;

import itplus.project.entity.DiemEntity;
import itplus.project.entity.KhoaHocMonHocEntity;
import itplus.project.pool.DBPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Dung NT
 */
public class DiemModel {

    public DiemModel() {
        DBPool db = new DBPool();
    }

    public ArrayList<DiemEntity> getAllDiemSinhVienFromMaLopAndMaMon(String MaLop, String MaMon) throws Exception {
        ArrayList<DiemEntity> arrDiem = new ArrayList<DiemEntity>();
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT SINHVIEN.MaSV, SINHVIEN.TenSV, DIEM.DiemLan1, DIEM.DiemLan2, DIEM.DiemLan3, DIEM.ID_Diem, VIEW_DANHSACHMONHOC_LOPHOC.MaMonHoc FROM SINHVIEN"
                    + " INNER JOIN VIEW_DANHSACHMONHOC_LOPHOC ON SINHVIEN.MaLop = VIEW_DANHSACHMONHOC_LOPHOC.MaLop"
                    + " INNER JOIN DIEM ON SINHVIEN.MaSV = DIEM.MaSV AND DIEM.MaMonHoc = VIEW_DANHSACHMONHOC_LOPHOC.MaMonHoc"
                    + " WHERE SINHVIEN.MaLop = ? AND VIEW_DANHSACHMONHOC_LOPHOC.MaMonHoc = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaLop);
            stmt.setString(2, MaMon);
            rs = stmt.executeQuery();
            while (rs.next()) {
                DiemEntity diem = new DiemEntity();
                diem.setMaSinhVien(rs.getString(1));
                diem.setTenSinhVien(rs.getString(2));
                diem.setDiemLan1(Integer.parseInt(rs.getString(3)));
                diem.setDiemLan2(Integer.parseInt(rs.getString(4)));
                diem.setDiemLan3(Integer.parseInt(rs.getString(5)));
                diem.setIdDiemSinhVien(Integer.parseInt(rs.getString(6)));
                diem.setMaMon(rs.getString(7));
                arrDiem.add(diem);
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
        return arrDiem;
    }
    
    public ArrayList<DiemEntity> getAllSinhVienFromMaLopAndMaMon(String MaLop, String MaMon) throws Exception {
        ArrayList<DiemEntity> arrDiem = new ArrayList<DiemEntity>();
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * FROM SINHVIEN INNER JOIN VIEW_DANHSACHMONHOC_LOPHOC ON SINHVIEN.MaLop = VIEW_DANHSACHMONHOC_LOPHOC.MaLop"
                    + " WHERE SINHVIEN.MaLop = ? AND VIEW_DANHSACHMONHOC_LOPHOC.MaMonHoc = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaLop);
            stmt.setString(2, MaMon);
            rs = stmt.executeQuery();
            while (rs.next()) {
                DiemEntity diem = new DiemEntity();
                diem.setMaSinhVien(rs.getString(1));
                diem.setTenSinhVien(rs.getString(2));
                arrDiem.add(diem);
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
        return arrDiem;
    }
    
    public int addDiemSinhVien(String MaMon, String MaSinhVien) throws SQLException {
        int id = 0;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "insert into DIEM values(?,?,?,?,?)";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, MaMon);
            stmt.setString(2, MaSinhVien);
            stmt.setString(3, String.valueOf(0));
            stmt.setString(4, String.valueOf(0));
            stmt.setString(5, String.valueOf(0));
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
    
    

    public void editDiem(DiemEntity diem, String MaMonHoc, String MaSinhVien) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "update DIEM set DiemLan1 = ?, DiemLan2 = ?, DiemLan3 = ? WHERE MaMonHoc = ? AND MaSV = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, String.valueOf(diem.getDiemLan1()));
            stmt.setString(2, String.valueOf(diem.getDiemLan2()));
            stmt.setString(3, String.valueOf(diem.getDiemLan3()));
            stmt.setString(4, MaMonHoc);
            stmt.setString(5, MaSinhVien);
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
