/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.model;

import itplus.project.entity.KhoaHocEntity;
import itplus.project.entity.KhoaHocMonHocEntity;
import itplus.project.entity.SinhVienEntity;
import itplus.project.pool.DBPool;
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
public class KhoaHocMonHocModel {

    public KhoaHocMonHocModel() {
        DBPool db = new DBPool();

    }

    public ArrayList<KhoaHocMonHocEntity> getAllKhoaHocMonHoc() throws Exception {
        ArrayList<KhoaHocMonHocEntity> arr = new ArrayList<KhoaHocMonHocEntity>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
            cn = DBPool.getConnection();
            stmt = cn.createStatement();
            String SQL = "SELECT KHOAHOC_MONHOC.IdKhoaHocMonHoc, KHOAHOC_MONHOC.MaKhoaHoc, KHOAHOC.TenKhoaHoc, KHOAHOC_MONHOC.MaHocKy, HOCKY.TenHocKy, KHOAHOC_MONHOC.MaMonHoc, MONHOC.TenMonHoc, KHOAHOC_MONHOC.ThuTu from KHOAHOC_MONHOC"
                    + " INNER JOIN HOCKY ON KHOAHOC_MONHOC.MaHocKy = HOCKY.MaHocKy"
                    + " INNER JOIN KHOAHOC ON KHOAHOC_MONHOC.MaKhoaHoc = KHOAHOC.MaKhoaHoc"
                    + " INNER JOIN MONHOC ON KHOAHOC_MONHOC.MaMonHoc = MONHOC.MaMonHoc";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                KhoaHocMonHocEntity k = new KhoaHocMonHocEntity();
                k.setIdKhoaHocMonHoc(Integer.parseInt(rs.getString(1)));
                k.setMaKhoaHoc(rs.getString(2));
                k.setTenKhoaHoc(rs.getString(3));
                k.setMaHocKy(rs.getString(4));
                k.setTenHocKy(rs.getString(5));
                k.setMaMonHoc(rs.getString(6));
                k.setTenMonHoc(rs.getString(7));
                k.setThuTu(Integer.parseInt(rs.getString(8)));
                arr.add(k);
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

    public int addKhoaHocMonHoc(KhoaHocMonHocEntity k) throws SQLException {
        int id = 0;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "insert into KHOAHOC_MONHOC values(?,?,?,?)";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, k.getMaHocKy());
            stmt.setString(2, k.getMaKhoaHoc());
            stmt.setString(3, k.getMaMonHoc());
            stmt.setString(4, String.valueOf(k.getThuTu()));
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

    public void editKhoaHocMonHoc(KhoaHocMonHocEntity k) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "update KHOAHOC_MONHOC set MaHocKy = ?, MaKhoaHoc = ?, MaMonHoc = ?, ThuTu = ? WHERE IdKhoaHocMonHoc = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, k.getMaHocKy());
            stmt.setString(2, k.getMaKhoaHoc());
            stmt.setString(3, k.getMaMonHoc());
            stmt.setString(4, String.valueOf(k.getThuTu()));
            stmt.setString(5, String.valueOf(k.getIdKhoaHocMonHoc()));
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

    public void deleteKhoaHocMonHoc(ArrayList<KhoaHocMonHocEntity> arr) throws SQLException, Exception {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBPool.getConnection();
            conn.setAutoCommit(false);//tao transaction
            for (KhoaHocMonHocEntity k : arr) {
                String SQL = "delete KHOAHOC_MONHOC where IdKhoaHocMonHoc = ? ";
                stmt = conn.prepareStatement(SQL);
                stmt.setString(1, String.valueOf(k.getIdKhoaHocMonHoc()));
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

}
