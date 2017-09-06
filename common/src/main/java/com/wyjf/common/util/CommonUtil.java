package com.wyjf.common.util;

import sun.misc.BASE64Encoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DecimalFormat;
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


    private static final String pwdstr = "wuyou";
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

    /**
     * 对字符串进行MD5加密
     *
     * @param s
     * @return String
     */
    public final static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 对明文密码进行加密操作，并返回加密码后的密码
     *
     * @param pwd
     *            明文密码
     * @return
     */
    public final static String generatePwd(String pwd) {
        return CommonUtil.MD5(CommonUtil.MD5(pwd + pwdstr));
    }

    /**
     * 检查o是否为空
     *
     * @param o
     *            要检查的对象
     * @return
     */
    public static Boolean checkEmpty(Object o) {
        if (o == null) {
            return true;
        } else if ("".equals(o.toString())) {
            return true;
        } else if ("null".equalsIgnoreCase(o.toString())) {
            return true;
        } else {
            return false;
        }
    }
}
