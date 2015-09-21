/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.model;

import itplus.project.entity.DiemEntity;
import itplus.project.entity.SinhVienEntity;
import itplus.project.pool.DBPool;
import java.sql.Connection;
import java.sql.Date;
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
            String SQL = "SELECT SINHVIEN.*, LOPHOC.TenLop FROM SINHVIEN INNER JOIN LOPHOC ON SINHVIEN.MaLop = LOPHOC.MaLop";
            rs = stmt.executeQuery(SQL);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            while (rs.next()) {
                SinhVienEntity sv = new SinhVienEntity();
                sv.setMaSinhVien(rs.getString(1));
                sv.setTenSinhVien(rs.getString(2));
                sv.setNgaySinhView(format.format(rs.getDate(3)));
                sv.setGioiTinh(rs.getString(4));
                sv.setSDT(rs.getString(5));
                sv.setDiaChi(rs.getString(6));
                sv.setQueQuan(rs.getString(7));
                sv.setEmail(rs.getString(8));
                sv.setMaLop(rs.getString(9));
                sv.setTenLop(rs.getString(11));
                sv.setNgaySinh(rs.getDate(3));
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            String SQL = "insert into SINHVIEN values(?,?,?,?,?,?,?,?,?,?)";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, sv.getMaSinhVien());
            stmt.setString(2, sv.getTenSinhVien());
            stmt.setString(3, format.format(sv.getNgaySinh()));
            stmt.setString(4, sv.getGioiTinh());
            stmt.setString(5, sv.getSDT());
            stmt.setString(6, sv.getDiaChi());
            stmt.setString(7, sv.getQueQuan());
            stmt.setString(8, sv.getEmail());
            stmt.setString(9, sv.getMaLop());
            stmt.setString(10, "svitplus");
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            String SQL = "update SINHVIEN set TenSV = ?, NgaySinh = ?, GioiTinh = ?, SDT = ?, DiaChi = ?, QueQuan = ?, Email = ?, MaLop = ? WHERE MaSV = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, sv.getTenSinhVien());
            stmt.setString(2, format.format(sv.getNgaySinh()));
            stmt.setString(3, sv.getGioiTinh());
            stmt.setString(4, sv.getSDT());
            stmt.setString(5, sv.getDiaChi());
            stmt.setString(6, sv.getQueQuan());
            stmt.setString(7, sv.getEmail());
            stmt.setString(8, sv.getMaLop());
            stmt.setString(9, sv.getMaSinhVien());
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

    public void deleteSinhVien(ArrayList<SinhVienEntity> arr) throws SQLException, Exception {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBPool.getConnection();
            conn.setAutoCommit(false);//tao transaction
            for (SinhVienEntity SinhVien : arr) {
                String SQL = "delete SINHVIEN  where MaSV = ? ";
                stmt = conn.prepareStatement(SQL);
                stmt.setString(1, SinhVien.getMaSinhVien());
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

    public boolean checkDuplicateMaSinhVien(String MaSV) throws Exception {

        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * from SINHVIEN WHERE MaSV = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaSV);
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

    // lay ve danh sach nhung mon hoc thuoc lop
    public ArrayList<String> getMaMonFormMaLop(String MaLop) throws Exception {
        ArrayList<String> arrMaMon = new ArrayList<String>();
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            
            String SQL = "SELECT * FROM View_getMonHonFromMaLop WHERE MaLop = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaLop);
            rs = stmt.executeQuery();
            while (rs.next()) {
                arrMaMon.add(rs.getString(2));
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
        return arrMaMon;
    }

    // kiem tra xem trong lop hoc da tao mon hoc chua
    public boolean checkCreateMonHoc(String MaLop, String MaMonHoc) throws Exception {
        boolean check = false;
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT LOPHOC.MaLop, KHOAHOC_MONHOC.MaMonHoc FROM KHOAHOC"
                    + " INNER JOIN LOPHOC ON KHOAHOC.MaKhoaHoc = LOPHOC.MaKhoaHoc"
                    + " INNER JOIN KHOAHOC_MONHOC ON KHOAHOC.MaKhoaHoc = KHOAHOC_MONHOC.MaKhoaHoc"
                    + " WHERE LOPHOC.MaLop = ? AND KHOAHOC_MONHOC.MaMonHoc = ?";

            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaLop);
            stmt.setString(2, MaMonHoc);
            rs = stmt.executeQuery();
            while (rs.next()) {
                check = true;
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
        return check;
    }

    // them sinh vien vao bang diem
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
    
    public void deleteDiemSinhVien(ArrayList<SinhVienEntity> arr) throws SQLException, Exception {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBPool.getConnection();
            conn.setAutoCommit(false);//tao transaction
            for (SinhVienEntity SinhVien : arr) {
                String SQL = "DELETE FROM DIEM WHERE MaSV = ?";
                stmt = conn.prepareStatement(SQL);
                stmt.setString(1, SinhVien.getMaSinhVien());
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
    
    public void deleteDiemFromMaSV(String MaSV) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "DELETE FROM DIEM WHERE MaSV = ?";
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
    
    // lay ve maLop cu
    public String getOldMaLop(String MaSV) throws Exception {
        String MaLop = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT MaLop FROM SINHVIEN WHERE MaSV = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaLop);
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
    
    // lay ve makhoahoc tu maLop
    public String getMaKhoaHocFromMaLop(String MaLop) throws Exception {
        String MaKhoaHoc = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT KHOAHOC.MaKhoaHoc FROM LOPHOC INNER JOIN KHOAHOC ON LOPHOC.MaKhoaHoc = KHOAHOC.MaKhoaHoc WHERE LOPHOC.MaLop = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaLop);
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

}
