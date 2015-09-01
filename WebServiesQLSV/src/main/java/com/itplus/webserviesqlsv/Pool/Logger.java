/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itplus.webserviesqlsv.Pool;

/**
 *
 * @author Dung NT
 */
public class Logger {

    private String classname;

    public Logger(String classname) {
        this.classname = classname;
    }

    public void log(String msg) {
        System.out.println("[" + this.classname + "] " + msg);
    }

    public void error(String msg) {
        System.err.println("[ERROR] [" + this.classname + "] " + msg);
    }
}
