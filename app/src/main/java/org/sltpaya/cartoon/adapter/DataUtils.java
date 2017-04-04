package org.sltpaya.cartoon.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Author: SLTPAYA
 * Date: 2017/3/11
 */
public class DataUtils {

    /**
     * 检查集合是否合法，是否为null，
     */
    public static boolean checkDataValidity(List list) {
        return list != null && list.size() != 0;
    }

    /**
     * 安全的整型转换
     * @param num 数字
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

    /**
     * 安全的长整型转换
     * @param num 需要转换的数字
     * @param defaultNum 出错时默认的数字
     * @return 结果
     */
    public static long parseLong(String num, long defaultNum) {
        long number = defaultNum;
        try {
            number = Long.parseLong(num);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return number;
    }

    /**
     * 安全的浮点型字符串转换
     * @param num 需要转换的字符串
     * @param defaultNum 当出现错误时，默认返回的值
     * @return 结果float
     */
    public static float parseFloat(String num, float defaultNum) {
        float number = defaultNum;
        try {
            number = Float.parseFloat(num);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return number;
    }

    /**
     * 解析数据为万，亿单位，保留一位小数
     * @param num 原始字符串数字
     * @param defaultNum 如果解析错误默认的数字
     * @return 字符串
     */
    public static String parseDataNum(String num, float defaultNum) {
        float floatNum = parseFloat(num, defaultNum);
        int tmp = (int) floatNum;
        int length = 0;
        while (tmp >= 10) {
            tmp = tmp / 10;
            length ++;
        }
        String str;
        if (length >= 8) {
            floatNum = ((int)(floatNum / 100000000 * 10)) / 10.0F;
            str = floatNum + "亿";
        } else if (length >= 4) {
            floatNum = ((int)(floatNum / 10000 * 10)) / 10.0F;
            str = floatNum + "万";
        } else {
            str = String.valueOf(floatNum);
        }
        return str;
    }

    /**
     * 获取作品更新频率
     * @param gxType id
     * @return 对应更新信息
     */
    public static String getBookFrequency(int gxType) {
        String[] bookFrequencys = {
                "每周更新",
                "一周两更",
                "一周三更",
                "隔日更新",
                "工作日更",
                "每日更新",
                "一周五更",
                "一周六更",
                "一周四更",
                "一日双更"
        };
        if (gxType > 0 && gxType <= bookFrequencys.length) {
            return bookFrequencys[gxType - 1];
        }
        return "";
    }

    /**
     * 获取作品相对于本地的更新时间：格式为：昨日16:01更新
     * @param timeMillis 时间
     * @return 格式化之后的日期
     */
    public static String getUpdateFormatDate(long timeMillis) {
        //如果是今天更新的：如果是12小时之内的，标记为多少小时之前更新的
        //如：4小时前更新 10分钟前更新
        //如果是昨天和前天更新的，加上时间:前天16:01更新
        //如果是前天之前的时候更新的，加入日期如：03-29更新
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.CHINA);

        long oneDayMillis = 86400000;//一天的毫秒数（24小时)
        long localTimeMillis = System.currentTimeMillis();
        long value = Math.abs(localTimeMillis - timeMillis);
        Date date = new Date(timeMillis);

        dateFormat.applyPattern("dd");
        String serverDay = dateFormat.format(new Date(timeMillis));
        String localDay = dateFormat.format(new Date(localTimeMillis));

        dateFormat.applyPattern("HH:mm");
        String time;
        //判断：如果本地事件和服务器时间相差小于一天的毫秒数，那就是当天更新的，否则就是
        if (serverDay.equals(localDay)) {
            if (value > 3600000) {
                time = (value/1000/60/60) + "小时前";
            } else {
                time = (value/1000/60) + "分钟前";
            }
        } else if (value < oneDayMillis * 2) {
            time = "昨天" + dateFormat.format(date);
        } else if (value < oneDayMillis * 3) {
            dateFormat.applyPattern("HH:mm");
            time = "前天"+ dateFormat.format(date);
        } else {
            dateFormat.applyPattern("MM-dd");
            time = dateFormat.format(date);
        }
        time = time + "更新";
        return time;
    }

}
