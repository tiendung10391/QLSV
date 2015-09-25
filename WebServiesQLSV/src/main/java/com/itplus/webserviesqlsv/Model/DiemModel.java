/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.Model;

import com.itplus.webserviesqlsv.Entity.DiemEntity;
import com.itplus.webserviesqlsv.Entity.DiemThiEntity;
import com.itplus.webserviesqlsv.Entity.HocKyEntity;
import com.itplus.webserviesqlsv.Entity.LopHocEntity;
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
 * @author kunph_000
 */
public class DiemModel {

    public DiemModel() {
        DBPool db = new DBPool();
    }

    public ArrayList<DiemThiEntity> getAllDiemKhoaHoc(String MaSV) throws Exception {
        ArrayList<DiemThiEntity> arr = new ArrayList<DiemThiEntity>();
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * FROM View_Diem_Lop"
                    + " INNER JOIN View_MonHoc_KhoaHoc"
                    + " ON View_Diem_Lop.MaLop = View_MonHoc_KhoaHoc.MaLop AND View_Diem_Lop.MaMonHoc = View_MonHoc_KhoaHoc.MaMonHoc"
                    + " WHERE View_Diem_Lop.MaSV = ?"
                    + " ORDER BY View_MonHoc_KhoaHoc.MaHocKy ASC";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaSV);
            rs = stmt.executeQuery();

            while (rs.next()) {
                DiemThiEntity diem = new DiemThiEntity();
                diem.setId_diem(Integer.parseInt(rs.getString(1)));
                diem.setMaMonHoc(rs.getString(2));
                diem.setDiemLan1(Integer.parseInt(rs.getString(4)));
                diem.setDiemLan2(Integer.parseInt(rs.getString(5)));
                diem.setDiemLan3(Integer.parseInt(rs.getString(6)));
                diem.setMaHocKy(rs.getString(15));
                diem.setTenMon(rs.getString(18));
                arr.add(diem);
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

    // lay ve danh sach diem cua hoc ky
    public ArrayList<DiemThiEntity> getAllDiemHocKy(String MaSV, String MaHocKy) throws Exception {
        ArrayList<DiemThiEntity> arr = new ArrayList<DiemThiEntity>();
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * FROM View_Diem_Lop"
                    + " INNER JOIN View_MonHoc_KhoaHoc"
                    + " ON View_Diem_Lop.MaLop = View_MonHoc_KhoaHoc.MaLop AND View_Diem_Lop.MaMonHoc = View_MonHoc_KhoaHoc.MaMonHoc"
                    + " WHERE View_Diem_Lop.MaSV = ? AND MaHocKy = ?"
                    + " ORDER BY View_MonHoc_KhoaHoc.MaHocKy ASC";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaSV);
            stmt.setString(2, MaHocKy);
            rs = stmt.executeQuery();

            while (rs.next()) {
                DiemThiEntity diem = new DiemThiEntity();
                diem.setId_diem(Integer.parseInt(rs.getString(1)));
                diem.setMaMonHoc(rs.getString(2));
                diem.setDiemLan1(Integer.parseInt(rs.getString(4)));
                diem.setDiemLan2(Integer.parseInt(rs.getString(5)));
                diem.setDiemLan3(Integer.parseInt(rs.getString(6)));
                diem.setMaHocKy(rs.getString(15));
                diem.setTenMon(rs.getString(18));
                arr.add(diem);
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

     // lay ve danh sach hoc ky cua sinh vien
    public ArrayList<HocKyEntity> getAllHocKy(String MaSV) throws Exception {
        ArrayList<HocKyEntity> arrHocKy = new ArrayList<HocKyEntity>();
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT MaHocKy FROM View_Diem_Lop"
                    + " INNER JOIN View_MonHoc_KhoaHoc"
                    + " ON View_Diem_Lop.MaLop = View_MonHoc_KhoaHoc.MaLop AND View_Diem_Lop.MaMonHoc = View_MonHoc_KhoaHoc.MaMonHoc"
                    + " WHERE View_Diem_Lop.MaSV = ?"
                    + " GROUP BY MaHocKy";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaSV);
            rs = stmt.executeQuery();

            while (rs.next()) {
                HocKyEntity hocKy = new HocKyEntity();
                hocKy.setMaHocKy(rs.getString(1));
                hocKy.setTenHocKy(setTenHocKy(rs.getString(1)));
                arrHocKy.add(hocKy);
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
        return arrHocKy;
    }
    
    public String setTenHocKy(String MaHK){
        if(MaHK.equals("HK1")){
            return "Học Kỳ 1";
        }else if(MaHK.equals("HK2")){
            return "Học Kỳ 2";
        }else if(MaHK.equals("HK3")){
            return "Học Kỳ 3";
        }else if(MaHK.equals("HK4")){
            return "Học Kỳ 4";
        }
                return "Học Kỳ 5";
    }

    // lay ve MaMon dang hoc dua vao MaSV
    public String getMaMonFormMaSV(String MaSV) throws Exception {
        String MaMon = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * FROM View_MonDangHoc WHERE MaSV = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaSV);
            rs = stmt.executeQuery();

            while (rs.next()) {
                MaMon = rs.getString(2);
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
        return MaMon;
    }

    // lay ve MaLop 
    public String getMaLopFormMaSV(String MaSV) throws Exception {
        String MaLop = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT MaLop FROM SINHVIEN WHERE MaSV = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaSV);
            rs = stmt.executeQuery();

            while (rs.next()) {
                MaLop = rs.getString(1);
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
        return MaLop;
    }

    // lay ve MaHocKy
    public String getMaHocKyFormMaSV(String MaLop, String MaMon) throws Exception {
        String MaHocKy = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT KHOAHOC_MONHOC.MaHocKy FROM KHOAHOC INNER JOIN LOPHOC ON KHOAHOC.MaKhoaHoc = LOPHOC.MaKhoaHoc"
                    + " INNER JOIN KHOAHOC_MONHOC ON KHOAHOC.MaKhoaHoc = KHOAHOC_MONHOC.MaKhoaHoc"
                    + " WHERE LOPHOC.MaLop = ? AND KHOAHOC_MONHOC.MaMonHoc = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaLop);
            stmt.setString(2, MaMon);
            rs = stmt.executeQuery();

            while (rs.next()) {
                MaHocKy = rs.getString(1);
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
        return MaHocKy;
    }

    public ArrayList<DiemEntity> getDiem(String masv) throws Exception {
        ArrayList<DiemEntity> arr = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
//            DBPool.build(2);
            cn = DBPool.getConnection();
//            cn = DBUtil.connectSQL();
            stmt = cn.createStatement();
            String SQL = "SELECT * from Diem where MaSV = '" + masv + "'";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                DiemEntity diem = new DiemEntity();
                diem.setMaMonHoc(rs.getString(2));
                diem.setMaSV(rs.getString(3));
                diem.setDiemLan1(rs.getInt(4));
                diem.setDiemlan2(rs.getInt(5));
                diem.setDiemLan3(rs.getInt(6));
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
            String SQL = "insert into DIEM values(?,?,?,?,?,?)";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
//            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, diem.getMaMonHoc());
            stmt.setString(2, diem.getMaSV());
            stmt.setInt(3, diem.getDiemLan1());
            stmt.setInt(4, diem.getDiemlan2());
            stmt.setInt(5, diem.getDiemLan3());
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

    public void editDiem(DiemEntity diem) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "update DIEM set DiemLan1 = ?, DiemLan2 = ?, DiemLan3 = ?, TrangThai = ? where MaSV = ? AND MaMonHoc = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, diem.getDiemLan1());
            stmt.setInt(2, diem.getDiemlan2());
            stmt.setInt(3, diem.getDiemLan3());
            stmt.setString(5, diem.getMaSV());
            stmt.setString(6, diem.getMaMonHoc());
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

    public void deleteDiem(String MaMonHoc, String MaSV) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "delete DIEM  where MaMonHoc = ? and MaSV = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaMonHoc);
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
            pstm.setString(1, "%" + MaSV + "%");
            rs = pstm.executeQuery(SQL);

            while (rs.next()) {
                DiemEntity diem = new DiemEntity();
                diem.setMaMonHoc(rs.getString(1));
                diem.setMaSV(rs.getString(2));
                diem.setDiemLan1(rs.getInt(3));
                diem.setDiemlan2(rs.getInt(4));
                diem.setDiemLan3(rs.getInt(5));
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
    
    public ArrayList<DiemEntity> getDiemHocKy(String masv,String hk) throws Exception {
        if(hk.equals("Tất cả")){
        ArrayList<DiemEntity> arr = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
//            DBPool.build(2);
            cn = DBPool.getConnection();
//            cn = DBUtil.connectSQL();
            stmt = cn.createStatement();
            String SQL = "SELECT * from Diem ";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                DiemEntity diem = new DiemEntity();
                diem.setMaMonHoc(rs.getString(2));
                diem.setMaSV(rs.getString(3));
                diem.setDiemLan1(rs.getInt(4));
                diem.setDiemlan2(rs.getInt(5));
                diem.setDiemLan3(rs.getInt(6));
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
        }else{
            ArrayList<DiemEntity> arr = new ArrayList<>();
            Statement stmt = null;
            ResultSet rs = null;
            Connection cn = null;
            try {
    //            DBPool.build(2);
                cn = DBPool.getConnection();
    //            cn = DBUtil.connectSQL();
                stmt = cn.createStatement();
                String SQL = "select Diem.MaMonHoc,Diem.MaSV,Diem.DiemLan1,Diem.DiemLan2,Diem.DiemLan3\n" +
                        "from Sinhvien,KHOAHOC_MONHOC,Diem,HocKy\n" +
                        "where Sinhvien.MaSV=Diem.MaSV and Diem.MaMonHoc=KHOAHOC_MONHOC.MaMonHoc \n" +
                        "and KHOAHOC_MONHOC.MaHocKy=HocKy.MaHocKy and Sinhvien.MaSV='"+masv+"' and HocKy.TenHocKy LIKE N'"+hk+"'";
                rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    DiemEntity diem = new DiemEntity();
                    diem.setMaMonHoc(rs.getString(1));
                    diem.setMaSV(rs.getString(2));
                    diem.setDiemLan1(rs.getInt(3));
                    diem.setDiemlan2(rs.getInt(4));
                    diem.setDiemLan3(rs.getInt(5));
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
    }
    
    public ArrayList<HocKyEntity> getHocKy() throws Exception {
        ArrayList<HocKyEntity> arr = new ArrayList<HocKyEntity>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
//            DBPool.build(2);
            cn = DBPool.getConnection();
//            cn = DBUtil.connectSQL();
            stmt = cn.createStatement();
            String SQL = "SELECT * from HOCKY";
            rs = stmt.executeQuery(SQL);

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
                DBPool.releaseConnection(cn, stmt, rs);
            } catch (Exception e) {
                throw e;
            }
        }
        return arr;
    }
    
    
    
    
    

}
