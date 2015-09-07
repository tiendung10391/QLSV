/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.Model;

import com.itplus.webserviesqlsv.Pool.DBPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Dung NT
 */
public class LoginModel {
    
    public LoginModel(){
        DBPool db = new DBPool();
    }
    
    // check username va password trong csdl
    public boolean checkLogin(String username, String password) throws Exception{
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            String SQL = "SELECT * FROM register WHERE username = ? AND password = ?";
            conn = DBPool.getConnection();
            
            stmt = conn.prepareStatement(SQL);
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            rs = stmt.executeQuery();
            if(rs.next()){
                return true;
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
        
        return false;
    }
    
}
