/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.model;


import itplus.project.entity.KhoaHocEntity;
import itplus.project.entity.NganhEntity;
import itplus.project.pool.DBPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Dung NT
 */
public class NganhModel {

    public NganhModel() {
        DBPool db = new DBPool();

    }

    public ArrayList<NganhEntity> getAllNganh() throws Exception {
        ArrayList<NganhEntity> arr = new ArrayList<NganhEntity>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
            cn = DBPool.getConnection();
            stmt = cn.createStatement();
            String SQL = "SELECT * from NGANH";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                NganhEntity nganh = new NganhEntity();
                nganh.setMaNganh(rs.getString(1));
                nganh.setTenNganh(rs.getString(2));
                arr.add(nganh);
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
    
    public String getInfoNganhFormMaNganh(String MaNganh) throws Exception {
        String TenNganh = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * from NGANH WHERE MaNganh = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaNganh);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TenNganh = rs.getString(2);
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
        return TenNganh;
    }

}
