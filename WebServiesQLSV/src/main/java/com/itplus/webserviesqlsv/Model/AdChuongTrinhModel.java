/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.Model;

import com.itplus.webserviesqlsv.Entity.AdChuongTrinhEntity;
import com.itplus.webserviesqlsv.Entity.ChuongTrinhEntity;
import com.itplus.webserviesqlsv.Entity.DiemThiEntity;
import com.itplus.webserviesqlsv.Entity.HocKyEntity;
import com.itplus.webserviesqlsv.Entity.KhoaHocEntity;
import com.itplus.webserviesqlsv.Pool.DBPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class AdChuongTrinhModel {

    public AdChuongTrinhModel() {
        DBPool dbp = new DBPool();
    }

    public String maKhoahoc(String malop) throws Exception {
        JSONObject obj = new JSONObject();
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "select MaKhoaHoc from LOPHOC where MaLop = '" + malop + "' ";
            conn = DBPool.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                obj.put("MaNganh", rs.getString(1));
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

    public ArrayList<AdChuongTrinhEntity> getMon(String makhoahoc) throws Exception {
        ArrayList<AdChuongTrinhEntity> arr = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
//            DBPool.build(2);
            cn = DBPool.getConnection();
//            cn = DBUtil.connectSQL();
            stmt = cn.createStatement();
            String SQL = "SELECT MONHOC.MaMonHoc,MONHOC.TenMonHoc,MONHOC.SoGio,MONHOC.Ghichu from MONHOC,KHOAHOC_MONHOC,KHOAHOC "
                    + "where MONHOC.MaMonHoc=KHOAHOC_MONHOC.MaMonHoc AND KHOAHOC_MONHOC.MaKhoaHoc= KHOAHOC.MaKhoaHoc AND KHOAHOC.MaKhoaHoc = '" + makhoahoc + "'";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                AdChuongTrinhEntity mon = new AdChuongTrinhEntity();
                mon.setMaMonHoc(rs.getString(1));
                mon.setTenMonHoc(rs.getString(2));
                mon.setSoGio(rs.getInt(3));
                mon.setGhiChu(rs.getString(4));
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

    // lay ve khoa hoc cua sinh vien dang hoc qua ma sinh vien
    public String getMaKhoaHocFormMaSV(String MaSV) throws Exception {
        String MaKhoaHoc = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT LOPHOC.MaKhoaHoc FROM SINHVIEN INNER JOIN LOPHOC ON SINHVIEN.MaLop = LOPHOC.MaLop WHERE SINHVIEN.MaSV = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaSV);
            rs = stmt.executeQuery();

            while (rs.next()) {
                MaKhoaHoc = rs.getString(1);
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
        return MaKhoaHoc;
    }

    // lay ve danh chuong trinh hoc qua ma khoa hoc
    public ArrayList<ChuongTrinhEntity> getAllChuongTrinhFromMaKhoaHoc(String MaKhoaHoc) throws Exception {
        ArrayList<ChuongTrinhEntity> arr = new ArrayList<ChuongTrinhEntity>();
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT MONHOC.MaMonHoc, MONHOC.TenMonHoc, MONHOC.SoGio, MONHOC.GhiChu, HOCKY.MaHocKy, HOCKY.TenHocKy, KHOAHOC.MaKhoaHoc FROM KHOAHOC_MONHOC"
                    + " INNER JOIN KHOAHOC ON KHOAHOC_MONHOC.MaKhoaHoc = KHOAHOC.MaKhoaHoc"
                    + " INNER JOIN HOCKY ON KHOAHOC_MONHOC.MaHocKy = HOCKY.MaHocKy"
                    + " INNER JOIN MONHOC ON KHOAHOC_MONHOC.MaMonHoc = MONHOC.MaMonHoc"
                    + " WHERE KHOAHOC.MaKhoaHoc = ?"
                    + " ORDER BY HOCKY.MaHocKy ASC";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaKhoaHoc);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ChuongTrinhEntity ct = new ChuongTrinhEntity();
                ct.setMaMon(rs.getString(1));
                ct.setTenMon(rs.getString(2));
                ct.setSoGio(Integer.parseInt(rs.getString(3)));
                ct.setGhiChu(rs.getString(4));
                ct.setMaHocKy(rs.getString(5));
                ct.setTenHocKy(rs.getString(6));
                ct.setMaKhoaHoc(rs.getString(7));

                arr.add(ct);
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

    // lay ve danh sach hoc ky tu ma khoa hoc
    public ArrayList<HocKyEntity> getAllHocKyFromMaKhoaHoc(String MaKhoaHoc) throws Exception {
        ArrayList<HocKyEntity> arr = new ArrayList<HocKyEntity>();
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT HOCKY.MaHocKy, HOCKY.TenHocKy FROM KHOAHOC_MONHOC"
                    + " INNER JOIN KHOAHOC ON KHOAHOC_MONHOC.MaKhoaHoc = KHOAHOC.MaKhoaHoc"
                    + " INNER JOIN HOCKY ON KHOAHOC_MONHOC.MaHocKy = HOCKY.MaHocKy"
                    + " INNER JOIN MONHOC ON KHOAHOC_MONHOC.MaMonHoc = MONHOC.MaMonHoc"
                    + " WHERE KHOAHOC.MaKhoaHoc = ?"
                    + " GROUP BY HOCKY.MaHocKy, HOCKY.TenHocKy"
                    + " ORDER BY HOCKY.MaHocKy ASC";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaKhoaHoc);
            rs = stmt.executeQuery();

            while (rs.next()) {
                HocKyEntity hk = new HocKyEntity();
                hk.setMaHocKy(rs.getString(1));
                hk.setTenHocKy(rs.getString(2));
                

                arr.add(hk);
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
    
    // lay ve danh sach cac khoa học
    public ArrayList<KhoaHocEntity> getAllKhoaHoc() throws Exception {
        ArrayList<KhoaHocEntity> arr = new ArrayList<KhoaHocEntity>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
            cn = DBPool.getConnection();
            stmt = cn.createStatement();
            String SQL = "SELECT * from KHOAHOC";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                KhoaHocEntity khoaHoc = new KhoaHocEntity();
                khoaHoc.setMaKhoaHoc(rs.getString(1));
                khoaHoc.setTenKhoaHoc(rs.getString(2));
                
                arr.add(khoaHoc);
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

}
