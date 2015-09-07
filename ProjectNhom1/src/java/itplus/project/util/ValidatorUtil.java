/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Dung NT
 */
public class ValidatorUtil {

    public static boolean isSpaceString(String value) {
        if (value.equals("")) {
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean isChuaNhapInt(Integer value){
        if(value == 0){
            return false;
        }else{
            return true;
        }
    }
    
    public static boolean isLengthCharacter(String value, int number){
        if(value.length() > number){
            return false;
        }else{
            return true;
        }
    }
}
