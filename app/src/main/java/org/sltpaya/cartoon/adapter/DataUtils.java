package org.sltpaya.cartoon.adapter;

import android.content.Intent;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/3/11
 */
public class DataUtils {


    /**
     * 检查集合是否合法，是否为null，
     */
    public static boolean checkDataValidity(List list) {
        if (list != null && list.size() != 0) {
            return true;
        }
        return false;
    }

    /**
     * 安全的整型转换
     * @param num 字符型数字
     * @param defaultNum 默认数字，当发生异常时
     * @return int
     */
    public static int parseInt(String num, int defaultNum) {
        int number = defaultNum;
        try {
            number = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return number;
    }


}
