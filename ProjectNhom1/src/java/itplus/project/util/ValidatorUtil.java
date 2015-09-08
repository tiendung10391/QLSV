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

    private static final String KYTUDACBIET = "[A-Za-z0-9]*";

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

    public static boolean isLengthCharacter(String value, int number) {
        if (value.length() > number) {
            return true;
        } else {
            return false;
        }
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
