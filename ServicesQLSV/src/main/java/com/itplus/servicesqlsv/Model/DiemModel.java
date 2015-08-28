/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.servicesqlsv.Model;

import com.itplus.servicesqlsv.Entity.DiemEntity;
import com.itplus.servicesqlsv.Pool.DBPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author kunph_000
 */
public class DiemModel {

    public DiemModel() {
         DBPool db = new DBPool();
    }
     public ArrayList<DiemEntity> getDiem() throws Exception {
        ArrayList<DiemEntity> arr = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
//            DBPool.build(2);
            cn = DBPool.getConnection();
//            cn = DBUtil.connectSQL();
            stmt = cn.createStatement();
            String SQL = "SELECT * from Diem";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                DiemEntity diem = new DiemEntity();
                diem.setMaMonHoc(rs.getString(1));
                diem.setMaSV(rs.getString(2));
                diem.setDiemLan1(rs.getInt(3));
                diem.setDiemlan2(rs.getInt(4));
                diem.setDiemLan3(rs.getInt(5));
                diem.setTrangThai(rs.getBoolean(6));
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
