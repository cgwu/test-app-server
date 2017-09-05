package com.wyjf.common.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/31 0031.
 */
public class CommonUtil {
    /**
     * 获取6位数随机数
     * @return
     */
    public static String getVerifyCode(){
        Double code = (Math.random()*9+1)*100000;
        return new Integer(code.intValue()).toString();
    }

    /**
     * yyyy/MM/dd HH:mm:ss转date
     * @param str
     * @param pattern
     * @return
     */
    public static Date stringToDateTime(String str, String pattern){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = LocalDateTime.parse(str, dtf).atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * yyyy/MM/dd转date
     * @param str
     * @param pattern
     * @return
     */
    public static Date stringToDate(String str, String pattern){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = LocalDate.parse(str, dtf).atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * datetime转string
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToString(Date date, String pattern){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return ldt.format(dtf);
    }

    public static void main(String[] str){
        System.out.println(dateToString(new Date(), "yyyy/MM/dd hh:mm:ss"));
        System.out.println(stringToDate("2017/09/04 10:47:48", "yyyy/MM/dd HH:mm:ss"));
        System.out.println(stringToDateTime("2017/09/04 10:47:48", "yyyy/MM/dd HH:mm:ss"));
    }
}
