/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.servicesqlsv.Model;

import com.itplus.servicesqlsv.Entity.LopHocEntity;
import com.itplus.servicesqlsv.Pool.DBPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author kunph_000
 */
public class LopHocModel {

    public LopHocModel() {
         DBPool db = new DBPool();
    }
    public ArrayList<LopHocEntity> getLophoc() throws Exception {
        ArrayList<LopHocEntity> arr = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
//            DBPool.build(2);
            cn = DBPool.getConnection();
//            cn = DBUtil.connectSQL();
            stmt = cn.createStatement();
            String SQL = "SELECT * from LopHoc";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
               LopHocEntity lop = new LopHocEntity();
               lop.setMaLop(rs.getString(1));
               lop.setTenLop(rs.getString(2));
               lop.setHeDaoTao(rs.getString(3));
               lop.setNamNhapHoc(rs.getString(4));
               lop.setSiSo(rs.getInt(5));
               lop.setMaNganh(rs.getString(6));
                arr.add(lop);
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
