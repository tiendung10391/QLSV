/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.servicesqlsv.Pool;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Dung NT
 */
public class DBPool {
    private static Logger logger = new Logger("DBPool");
    private static LinkedList pool = new LinkedList();
    public final static int MAX_CONNECTIONS = 10;
    public final static int INI_CONNECTIONS = 2;

    public DBPool() {
        build(INI_CONNECTIONS);
    }

    public static Connection makeDBConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://localhost\\TIENDUNG;databaseName=QLSV";
            String user = "sa";
            String pass = "tiendung";
            conn = DriverManager.getConnection(dbURL, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void build(int number) {
        logger.log("Establishing " + number + " connections...");
        Connection conn = null;
        release();
        for (int i = 0; i < number; i++) {
            try {
                conn = makeDBConnection();
            } catch (SQLException ex) {
                logger.log("Error: " + ex.getMessage());
            }
            if (conn != null) {
                pool.addLast(conn);
            }
        }
        logger.log("Number of connection: " + number);
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            synchronized (pool) {
                conn = (java.sql.Connection) pool.removeFirst();
            }
            if (conn == null) {
                conn = makeDBConnection();
            }
            try {
                conn.setAutoCommit(true);
            } catch (Exception ex) {
            }

        } catch (Exception e) {
            logger.error("Method getConnection(): Error executing >>>" + e.toString());
            try {
                logger.log("Make connection again.");
                conn = makeDBConnection();
                conn.setAutoCommit(true);
            } catch (SQLException e1) {
            }
            logger.log("" + conn);
        }
        return conn;
    }
   

    public static void putConnection(java.sql.Connection conn) {
        try { 
            if (conn == null || conn.isClosed()) {
                logger.log("putConnection: conn is null or closed: " + conn);
                return;
            }
            if (pool.size() >= MAX_CONNECTIONS) {
                conn.close();
                return;
            }
        } catch (SQLException ex) {
        }

        synchronized (pool) {
            pool.addLast(conn);
            pool.notify();
        }
    }


    public static void release() {
        logger.log("Closing connections in pool...");
        synchronized (pool) {
            for (Iterator it = pool.iterator(); it.hasNext();) {
                Connection conn = (Connection) it.next();
                try {
                    conn.close();
                } catch (SQLException e) {
                    logger.error(
                            "release: Cannot close connection! (maybe closed?)");
                }
            }
            pool.clear();
        }
        logger.log("Release connection OK");
    }
 

    public static void releaseConnection(Connection conn, PreparedStatement preStmt) {
        putConnection(conn);
        try {
            if (preStmt != null) {
                preStmt.close();
            }
        } catch (SQLException e) {
        }
    }


    public static void releaseConnection(Connection conn, PreparedStatement preStmt, ResultSet rs) {
        releaseConnection(conn, preStmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
        }
    }

    public static void releaseConnection(Connection conn, Statement Stmt, ResultSet rs) {
        putConnection(conn);
        try {
            if (Stmt != null) {
                Stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
        }
    }

    public static void releaseConnection(Connection conn, PreparedStatement preStmt, Statement stmt, ResultSet rs) {
        releaseConnection(conn, preStmt, rs);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
        }
    }

    public static void releaseConnection(Connection conn, CallableStatement cs, ResultSet rs) {
        putConnection(conn);
        try {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
        }
    }
}
