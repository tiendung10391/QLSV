/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.model;

import itplus.project.entity.GioHocEntity;
import itplus.project.entity.LopMonHocEntity;
import itplus.project.entity.MonHocEntity;
import itplus.project.entity.PhongHocEntity;
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
public class LopMonHocModel {

    public LopMonHocModel() {
        DBPool db = new DBPool();
    }

    public int addLopMonHocNgayNghi(LopMonHocEntity lopMonHoc) throws SQLException {
        int id = 0;
        PreparedStatement stmt = null;
        Connection conn = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            String SQL = "insert into LOPMONHOC values(?,?,?,?,?,?,?,?,?,?)";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, lopMonHoc.getMaMonHoc());
            stmt.setString(2, lopMonHoc.getMaLop());
            stmt.setString(3, lopMonHoc.getMaGioHoc());
            stmt.setString(4, lopMonHoc.getMaPhong());
            stmt.setString(5, format.format(lopMonHoc.getNgayBatDauHoc()));
            stmt.setString(6, format.format(lopMonHoc.getNgayNghiDKBatDau()));
            stmt.setString(7, format.format(lopMonHoc.getNgayNghiDKKetThuc()));
            stmt.setString(8, lopMonHoc.getNgayHoc());
            stmt.setString(9, lopMonHoc.getGiangVien());
            stmt.setString(10, format.format(lopMonHoc.getNgayThiDuKien()));
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

    public int addLopMonHoc(LopMonHocEntity lopMonHoc) throws SQLException {
        int id = 0;
        PreparedStatement stmt = null;
        Connection conn = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            String SQL = "INSERT INTO LOPMONHOC VALUES(?,?,?,?,?,?,?,?,?,?)";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, lopMonHoc.getMaMonHoc());
            stmt.setString(2, lopMonHoc.getMaLop());
            stmt.setString(3, lopMonHoc.getMaGioHoc());
            stmt.setString(4, lopMonHoc.getMaPhong());
            stmt.setString(5, format.format(lopMonHoc.getNgayBatDauHoc()));
            stmt.setString(6, "");
            stmt.setString(7, "");
            stmt.setString(8, lopMonHoc.getNgayHoc());
            stmt.setString(9, lopMonHoc.getGiangVien());
            stmt.setString(10, format.format(lopMonHoc.getNgayThiDuKien()));
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

    public void editLopMonHocFull(LopMonHocEntity lop) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            String SQL = "update LOPMONHOC set MaGioHoc = ?, MaPhong = ?, NgayBatDauHoc = ?, NgayNghiDKBatDau = ?, NgayNghiDKKetThuc = ?, NgayHoc = ?, GiangVien = ?, NgayThiDuKien = ? WHERE IdLopMonHoc = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, lop.getMaGioHoc());
//            stmt.setString(2, format.format(sv.getNgaySinh()));
            stmt.setString(2, lop.getMaPhong());
            stmt.setString(3, format.format(lop.getNgayBatDauHoc()));
            stmt.setString(4, format.format(lop.getNgayNghiDKBatDau()));
            stmt.setString(5, format.format(lop.getNgayNghiDKKetThuc()));
            stmt.setString(6, lop.getNgayHoc());
            stmt.setString(7, lop.getGiangVien());
            stmt.setString(8, format.format(lop.getNgayThiDuKien()));
            stmt.setInt(9, lop.getIdLopMonHoc());
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

    public void editLopMonHoc(LopMonHocEntity lop) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            String SQL = "update LOPMONHOC set MaGioHoc = ?, MaPhong = ?, NgayBatDauHoc = ?, NgayHoc = ?, GiangVien = ?, NgayThiDuKien = ? WHERE IdLopMonHoc = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, lop.getMaGioHoc());
//            stmt.setString(2, format.format(sv.getNgaySinh()));
            stmt.setString(2, lop.getMaPhong());
            stmt.setString(3, format.format(lop.getNgayBatDauHoc()));
            stmt.setString(6, lop.getNgayHoc());
            stmt.setString(7, lop.getGiangVien());
            stmt.setString(8, format.format(lop.getNgayThiDuKien()));
            stmt.setInt(9, lop.getIdLopMonHoc());
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

   public void deleteLopMonHoc(ArrayList<LopMonHocEntity> arr) throws SQLException, Exception {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBPool.getConnection();
            conn.setAutoCommit(false);//tao transaction
            for (LopMonHocEntity lopHoc : arr) {
                String SQL = "delete LOPMONHOC  where idLopMonHoc = ? ";
                stmt = conn.prepareStatement(SQL);
                stmt.setInt(1, lopHoc.getIdLopMonHoc());
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

    public int addNgayHoc(int idLopMonHoc, String NgayHoc) throws SQLException {
        int id = 0;
        PreparedStatement stmt = null;
        Connection conn = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            String SQL = "INSERT INTO NGAYHOC(IdLopMonHoc,NgayHoc) VALUES(?,?)";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, idLopMonHoc);
            stmt.setString(2, NgayHoc);
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


    public boolean checkDuplicateThoiKhoaBieu(String MaLop, String MaMon) throws Exception {

        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * from LOPMONHOC WHERE MaLop = ? AND MaMonHoc = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaLop);
            stmt.setString(2, MaMon);
            rs = stmt.executeQuery();
            while (rs.next()) {
                return true;
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
        return false;
    }

    // lay ve maLop cu
    public String getTenLop(String MaLop) throws Exception {
        String TenLop = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT TenLop FROM LOPHOC WHERE MaLop = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaLop);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TenLop = rs.getString(1);
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
        return TenLop;
    }

    public String getTenMon(String Ma) throws Exception {
        String Ten = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT TenMonHoc FROM MONHOC WHERE MaMonHoc = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, Ma);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Ten = rs.getString(1);
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
        return Ten;
    }

    public String getGioHoc(String Ma) throws Exception {
        String Ten = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT ThoiGian FROM GIOHOC WHERE MaGioHoc = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, Ma);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Ten = rs.getString(1);
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
        return Ten;
    }

    public String getTenPhongHoc(String Ma) throws Exception {
        String Ten = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT TenPhongHoc FROM PHONGHOC WHERE MaPhongHoc = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, Ma);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Ten = rs.getString(1);
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
        return Ten;
    }

    public ArrayList<LopMonHocEntity> getAllLopMonHoc() throws Exception {
        ArrayList<LopMonHocEntity> arrLopMonHoc = new ArrayList<LopMonHocEntity>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            cn = DBPool.getConnection();
            stmt = cn.createStatement();
            String SQL = "SELECT LOPMONHOC.*, MONHOC.TenMonHoc, MONHOC.SoGio, LOPHOC.TenLop, GIOHOC.ThoiGian, PHONGHOC.TenPhongHoc FROM LOPMONHOC"
                    + " INNER JOIN MONHOC ON LOPMONHOC.MaMonHoc = MONHOC.MaMonHoc"
                    + " INNER JOIN LOPHOC ON LOPMONHOC.MaLop = LOPHOC.MaLop"
                    + " INNER JOIN GIOHOC ON LOPMONHOC.MaGioHoc = GIOHOC.MaGioHoc"
                    + " INNER JOIN PHONGHOC ON LOPMONHOC.MaPhong = PHONGHOC.MaPhongHoc";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                LopMonHocEntity lopMonHoc = new LopMonHocEntity();
                lopMonHoc.setIdLopMonHoc(Integer.parseInt(rs.getString(1)));
                lopMonHoc.setMaMonHoc(rs.getString(2));
                lopMonHoc.setMaLop(rs.getString(3));
                lopMonHoc.setMaGioHoc(rs.getString(4));
                lopMonHoc.setMaPhong(rs.getString(5));
                lopMonHoc.setNgayBatDauHoc(rs.getDate(6));
                lopMonHoc.setNgayBatDauHocView(format.format(rs.getDate(6)));
                lopMonHoc.setNgayNghiDKBatDau(rs.getDate(7));
                lopMonHoc.setNgayNghiDKBatDauView(format.format(rs.getDate(7)));
                lopMonHoc.setNgayNghiDKKetThuc(rs.getDate(8));
                lopMonHoc.setNgayNghiDKKetThucView(format.format(rs.getDate(8)));
                lopMonHoc.setNgayHoc(rs.getString(9));
                lopMonHoc.setGiangVien(rs.getString(10));
                lopMonHoc.setNgayThiDuKienView(format.format(rs.getDate(11)));
                lopMonHoc.setTenMonHoc(rs.getString(12));
                lopMonHoc.setSoGio(Integer.parseInt(rs.getString(13)));
                lopMonHoc.setTenLop(rs.getString(14));
                lopMonHoc.setThoiGian(rs.getString(15));
                lopMonHoc.setTenPhongHoc(rs.getString(16));
                arrLopMonHoc.add(lopMonHoc);
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

        return arrLopMonHoc;
    }

    public ArrayList<PhongHocEntity> getAllPhongHoc() throws Exception {
        ArrayList<PhongHocEntity> arrPhongHoc = new ArrayList<PhongHocEntity>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
            cn = DBPool.getConnection();
            stmt = cn.createStatement();
            String SQL = "SELECT * FROM PHONGHOC";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                PhongHocEntity phongHoc = new PhongHocEntity();
                phongHoc.setMaPhongHoc(rs.getString(1));
                phongHoc.setTenPhongHoc(rs.getString(2));

                arrPhongHoc.add(phongHoc);
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

        return arrPhongHoc;
    }

    public ArrayList<GioHocEntity> getAllGioHoc() throws Exception {
        ArrayList<GioHocEntity> arrGioHoc = new ArrayList<GioHocEntity>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
            cn = DBPool.getConnection();
            stmt = cn.createStatement();
            String SQL = "SELECT * FROM GIOHOC ORDER BY MaGioHoc ASC";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                GioHocEntity gioHoc = new GioHocEntity();
                gioHoc.setMaGioHoc(rs.getString(1));
                gioHoc.setThoiGian(rs.getString(2));
                arrGioHoc.add(gioHoc);
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

        return arrGioHoc;
    }
}
