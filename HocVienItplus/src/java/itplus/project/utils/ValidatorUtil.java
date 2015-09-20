/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itplus.project.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Dung NT
 */
public class ValidatorUtil {

    private static final String KYTUDACBIET = "[A-Za-z0-9]*";
    private static final String SDT = "[0-9]*";
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean isEmail(String value) {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static boolean isSpaceString(String value) {
        if (value.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isChuaNhapInt(Integer value) {
        if (value == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSelector(String value) {
        if (value.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isMinLenght(String Value, int lenght) {
        if (Value.length() < lenght) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isLengthCharacter(String value, int number) {
        if (value.length() < number) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNumber(String value) {
        Pattern pattern;
        Matcher matcher;
        try {
            pattern = Pattern.compile(SDT);
            matcher = pattern.matcher(value);
        } catch (Exception ex) {
            return false;
        }
        return matcher.matches();
    }
    

    public static boolean isNotKyThuDacBiet(String value) {
        Pattern pattern;
        Matcher matcher;
        try {
            pattern = Pattern.compile(KYTUDACBIET);
            matcher = pattern.matcher(value);
        } catch (Exception ex) {
            return false;
        }
        return matcher.matches();
    }
    
    
    
    
}
