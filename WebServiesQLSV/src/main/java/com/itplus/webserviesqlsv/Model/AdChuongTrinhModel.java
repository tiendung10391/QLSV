/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itplus.webserviesqlsv.Model;

import com.itplus.webserviesqlsv.Entity.AdChuongTrinhEntity;
import com.itplus.webserviesqlsv.Pool.DBPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class AdChuongTrinhModel {
    public AdChuongTrinhModel(){
        DBPool dbp = new DBPool();
    }
    public String maKhoahoc(String malop) throws Exception{
        JSONObject obj = new JSONObject() ;
        Statement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "select MaKhoaHoc from LOPHOC where MaLop = '"+malop+"' ";
            conn = DBPool.getConnection();         
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                obj.put("MaNganh", rs.getString(1));       
            }
        }catch(Exception ex){
            throw new Exception();
        }
        finally {
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
                    + "where MONHOC.MaMonHoc=KHOAHOC_MONHOC.MaMonHoc AND KHOAHOC_MONHOC.MaKhoaHoc= KHOAHOC.MaKhoaHoc AND KHOAHOC.MaKhoaHoc = '"+makhoahoc+ "'";
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
}
