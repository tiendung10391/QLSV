/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.util;

/**
 *
 * @author luand_000
 */
public class Log {
    private String Tag;

    public Log(String Tag) {
        this.Tag = Tag;
    }
    
    public void Log(String msg){
        System.out.println("[" + this.Tag + "] " + msg);
    }
    
    public void LogError(String msg){
        System.out.println("[ERROR] [" + this.Tag + "] " + msg);
    }
}
