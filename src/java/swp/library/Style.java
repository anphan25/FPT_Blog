/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swp.library;

import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class Style {

    public static String convertTagToString(ArrayList<String> arr) {
        String result = "";
        int length = arr.size();
        for (int i = 0; i < length; ++i) {
            if (i == 0) {
                result += arr.get(i);
            }
            result += " " + arr.get(i);
        }
        return result;
    }

    public static ArrayList<String> convertTagToArrayList(String str) {
        String[] arrStr = str.split("\\s");
        int length = arrStr.length;
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < length; i++) {
            list.add(arrStr[i]);
        }
        return list;
    }
    
    public static String convertToDateFormat(int d, int m, int y, int h, int mi) {//day:monnth:year  hour:minute
        String result = new String();
        if (d < 10) {
            result += "0" + d + "/";
        } else {
            result += d + "/";
        }
        if (m < 10) {
            result += "0" + m + "/";
        } else {
            result += m + "/";
        }
        result += y + "  ";
        if (h < 10) {
            result += "0" + h + ":";
        } else {
            result += h + ":";
        }
        if (mi < 10) {
            result += "0" + mi;
        } else {
            result += mi;
        }
        return result;
    }

}
