package com.wyjf.app.util;

import com.wyjf.common.util.CommonUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2017/9/4.
 * http://www.cnblogs.com/comeboo/p/5378922.html
 * 新的时间及日期API位于java.time中，下面是一些关键类
 * ●Instant——它代表的是时间戳
 * ●LocalDate——不包含具体时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等。
 * ●LocalTime——它代表的是不含日期的时间
 * ●LocalDateTime——它包含了日期及时间，不过还是没有偏移信息或者说时区。
 * ●ZonedDateTime——这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的。
 */
public class TestDateTime {
    private static final Logger log = LoggerFactory.getLogger(TestDateTime.class);

    //线程安全
    private static final DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");

    @Test
    public void testDate() {
        LocalDate ld = LocalDate.now();
        log.info(ld.toString());
        LocalTime lt = LocalTime.now();
        log.info(lt.toString());
        LocalDateTime ldt = LocalDateTime.now();
        log.info(ldt.toString());
        ZonedDateTime zdt = ZonedDateTime.now();
        log.info(zdt.toString());

        Instant timestamp = Instant.now();
        log.info(timestamp.toString());
    }


    @Test
    public void TestDateTimeFormat() {
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss.SSS");
        String str = ldt.format(format);
        log.info(str);

        String str2 = ldt.format(format2);
        log.info(str2);
        log.info("长度:{}", str2.length());

        Random random = new Random();
//        IntStream intStream = random.ints(0, 100);
        for (int i = 0; i < 10; i++) {
            String s = String.format("%03d", random.nextInt(1000));
//            log.info("随机整数:{}", random.nextInt(1000));
            log.info(s);
        }
    }

    @Test
    public void TestSerialNO() {
        for (int i = 0; i < 10; i++) {
            log.info(CommonUtil.getSerialNO());
        }
    }


    @Test
    public void testMinus(){
        log.info(LocalDateTime.now().minusMinutes(10).toString());
    }

}
