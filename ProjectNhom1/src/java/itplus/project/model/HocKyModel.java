/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.model;

import itplus.project.entity.HocKyEntity;
import itplus.project.entity.KhoaHocEntity;
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
public class HocKyModel {

    public HocKyModel() {
        DBPool db = new DBPool();
    }
    
    public ArrayList<HocKyEntity> getAllHocKy() throws Exception {
        ArrayList<HocKyEntity> arr = new ArrayList<HocKyEntity>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
            cn = DBPool.getConnection();
            stmt = cn.createStatement();
            String SQL = "SELECT * from HOCKY ORDER BY ThuTu ASC";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                HocKyEntity hocKy = new HocKyEntity();
                hocKy.setMaHocKy(rs.getString(1));
                hocKy.setTenHocKy(rs.getString(2));
                arr.add(hocKy);
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
    
    public String getTenHocKy(String MaHocKy) throws Exception {
        String TenHocKy = "";
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT TenHocKy from HOCKY WHERE MaHocKy = ?";
            conn = DBPool.getConnection();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, MaHocKy);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TenHocKy = rs.getString(1);
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
        return TenHocKy;
    }
}
