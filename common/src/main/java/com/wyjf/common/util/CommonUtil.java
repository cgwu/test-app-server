package com.wyjf.common.util;

import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2017/8/31 0031.
 */
public class CommonUtil {


    private static final String pwdstr = "wuyou";

    /**
     * 获取6位数随机数
     *
     * @return
     */
    public static String getVerifyCode() {
        Double code = (Math.random() * 9 + 1) * 100000;
        return new Integer(code.intValue()).toString();
    }

    /**
     * yyyy/MM/dd HH:mm:ss转date
     *
     * @param str
     * @param pattern
     * @return
     */
    public static Date stringToDateTime(String str, String pattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = LocalDateTime.parse(str, dtf).atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * yyyy/MM/dd转date
     *
     * @param str
     * @param pattern
     * @return
     */
    public static Date stringToDate(String str, String pattern) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = LocalDate.parse(str, dtf).atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * datetime转string
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToString(Date date, String pattern) {
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
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

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
     * @param pwd 明文密码
     * @return
     */
    public final static String generatePwd(String pwd) {
        return CommonUtil.MD5(CommonUtil.MD5(pwd + pwdstr));
    }

    /**
     * 检查o是否为空
     *
     * @param o 要检查的对象
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

    /**
     * 获取当前时间加天数
     * days 传进来的天数，负数减天数
     *
     * @return
     */
    public static LocalDateTime getTokenDateTime(int days) {
        LocalDateTime time = LocalDateTime.now();
        return time.plusDays(days);
    }

    //线程安全
    private static final DateTimeFormatter fmtSerialNO = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");

    /**
     * 按时间获取流水号
     *
     * @return
     */
    public static String getSerialNO() {
        Random random = new Random();
        return LocalDateTime.now().format(fmtSerialNO) + String.format("%03d", random.nextInt(1000));
    }

    /**
     * 根据用户名的不同长度，来进行替换 ，达到保密效果
     *
     * @param userName 用户名
     * @return 替换后的用户名
     */
    public static String userNameReplaceWithStar(String userName) {
        String userNameAfterReplaced = "";

        if (userName == null) {
            userName = "";
        }

        int nameLength = userName.length();
        System.out.println(nameLength);

        if (nameLength <= 1) {
            userNameAfterReplaced = "*";
        } else if (nameLength == 2) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\w{0})\\w(?=\\w{1})");
        } else if (nameLength >= 3 && nameLength <= 6) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{1})\\d(?=\\d{1})");
        } else if (nameLength == 7) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{1})\\d(?=\\d{2})");
        } else if (nameLength == 8) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{2})\\d(?=\\d{2})");
        } else if (nameLength == 9) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{2})\\d(?=\\d{3})");
        } else if (nameLength == 10) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{3})\\d(?=\\d{3})");
        } else if (nameLength >= 11) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{3})\\d(?=\\d{4})");
        }

        return userNameAfterReplaced;

    }

    /**
     * 实际替换动作
     *
     * @param username username
     * @param regular  正则
     * @return
     */
    private static String replaceAction(String username, String regular) {
        return username.replaceAll(regular, "*");
    }

    /**
     * 身份证号替换，保留前四位和后四位
     * <p>
     * 如果身份证号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param idCard 身份证号
     * @return
     */


    /**public static String idCardReplaceWithStar(String idCard) {

     if (idCard.isEmpty() || idCard == null) {
     return null;
     } else {
     return replaceAction(idCard, "(?<=\\d{4})\\d(?=\\d{4})");
     }
     }
     * 银行卡替换，保留后四位
     * <p>
     * 如果银行卡号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param bankCard 银行卡号
     * @return
     */
    public static String bankCardReplaceWithStar(String bankCard) {
        if (StringUtils.isEmpty(bankCard)) {
            return null;
        } else {
            return replaceAction(bankCard, "(?<=\\d{0})\\d(?=\\d{4})");
        }
    }

}
