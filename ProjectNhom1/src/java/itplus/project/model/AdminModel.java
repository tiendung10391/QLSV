/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.model;

import itplus.project.entity.AdminEntity;
import itplus.project.entity.MonHocEntity;
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
public class AdminModel {

    public AdminModel() {
        DBPool db = new DBPool();

    }
    
    public ArrayList<AdminEntity> getAllAdmin() throws Exception {
        ArrayList<AdminEntity> arr = new ArrayList<AdminEntity>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
            cn = DBPool.getConnection();
            stmt = cn.createStatement();
            String SQL = "SELECT * FROM ADMIN";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                AdminEntity admin = new AdminEntity();
                admin.setId(rs.getInt(1));
                admin.setTenDangNhap(rs.getString(2));
                admin.setMatKhau(rs.getString(3));
                admin.setDiaChi(rs.getString(4));
                admin.setEmail(rs.getString(5));
                admin.setTenNguoiDung(rs.getString(6));
                admin.setSDT(rs.getString(7));
                arr.add(admin);
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
    
    public int addAdmin(AdminEntity admin) throws SQLException {
        int id = 0;
        PreparedStatement stmt = null;
        Connection conn = null;
        try {

            String SQL = "insert into ADMIN values(?,?,?,?,?,?)";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
//            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, admin.getTenDangNhap());
            stmt.setString(2, admin.getMatKhau());
            stmt.setString(3, admin.getDiaChi());
            stmt.setString(4, admin.getEmail());
            stmt.setString(5, admin.getTenNguoiDung());
            stmt.setString(6, admin.getSDT());
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
    
    public void editAdmin(AdminEntity admin) throws SQLException {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            String SQL = "UPDATE ADMIN SET MatKhau = ?, DiaChi = ?, Email = ?, TenNguoiDung = ?, SDT = ? where id = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, admin.getMatKhau());
            stmt.setString(2, admin.getDiaChi());
            stmt.setString(3, admin.getEmail());
            stmt.setString(4, admin.getTenNguoiDung());
            stmt.setString(5, admin.getSDT());
            stmt.setInt(6, admin.getId());
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
    
    public void deleteAdmin(ArrayList<AdminEntity> arr) throws SQLException, Exception {
        PreparedStatement stmt = null;
        Connection conn = null;
        try {
            conn = DBPool.getConnection();
            conn.setAutoCommit(false);//tao transaction
            for (AdminEntity admin : arr) {
                String SQL = "DELETE ADMIN  where id = ? ";
                stmt = conn.prepareStatement(SQL);
                stmt.setInt(1, admin.getId());
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
