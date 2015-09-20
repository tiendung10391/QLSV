/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itplus.webserviesqlsv.Model;

import com.itplus.webserviesqlsv.Entity.AdChuongTrinhEntity;
import com.itplus.webserviesqlsv.Entity.AdLichHocEntity;
import com.itplus.webserviesqlsv.Pool.DBPool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class AdLichHocModel {
    public AdLichHocModel(){
        DBPool dbp = new DBPool();
    }
    public ArrayList<AdLichHocEntity> getLich(String lop) throws Exception {
        ArrayList<AdLichHocEntity> arr = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection cn = null;
        try {
//            DBPool.build(2);
            cn = DBPool.getConnection();
//            cn = DBUtil.connectSQL();
            stmt = cn.createStatement();
            String SQL = "Select MONHOC.TenMonHoc,GIOHOC.Thoigian,PHONGHOC.TenPhongHoc,LOPMONHOC.NgayHoc \n" +
                            "from LOPMONHOC,LOPHOC,GIOHOC,PHONGHOC,MONHOC\n" +
                            "where LOPMONHOC.MaLop=LOPHOC.MaLop and LOPMONHOC.MaMonHoc= MONHOC.MaMonHoc and\n" +
                                "LOPMONHOC.MaPhong=PHONGHOC.MaPhongHoc and LOPMONHOC.MaGioHoc = GIOHOC.MaGioHoc\n" +
                                "and LOPHOC.MaLop ='"+lop+"'";
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                AdLichHocEntity lich = new AdLichHocEntity();
                lich.setMonHoc(rs.getString(1));
                lich.setGioHoc(rs.getString(2));
                lich.setPhong(rs.getString(3));
                lich.setNgay(rs.getString(4));
                arr.add(lich);
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
