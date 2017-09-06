package com.wyjf.common.repository;

import com.wyjf.common.domain.StockData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/9/1 0001.
 */
public interface StockDataRepo extends JpaRepository<StockData, Long> {
    @Query(value = "SELECT tsd1.code, tsd1.name, tsd1.open, tsd1.yestclose, tsd1.high, tsd1.low, tsd1.price, DATE_FORMAT(tsd1.time_time,'%Y/%m/%d %H:%i') time_time " +
            "FROM t_stock_data tsd1 " +
            "WHERE tsd1.time_time IN ( " +
            "SELECT " +
            "MAX(tsd.time_time) maxtime " +
            "FROM " +
            "t_stock_data tsd " +
            "WHERE " +
            "1=1 AND DATE_FORMAT(tsd.time_time, '%Y/%m/%d') = DATE_FORMAT(:date1, '%Y/%m/%d') " +
            "AND (DATE_FORMAT(tsd.time_time, '%H:%i') >= '09:30' " +
            "AND DATE_FORMAT(tsd.time_time, '%H:%i') <= '11:30') " +
            "OR (DATE_FORMAT(tsd.time_time, '%H:%i') >= '13:00'" +
            "AND DATE_FORMAT(tsd.time_time, '%H:%i') <= '15:00') " +
            "GROUP BY " +
            "DATE_FORMAT(tsd.time_time, '%Y/%m/%d %H:%i')) " +
            "ORDER BY tsd1.time_time DESC", nativeQuery = true)
    public List findSockDataMin(@Param("date1") String date);

    @Query(value = "SELECT tsd1.code, tsd1.name, tsd1.open, tsd1.yestclose, tsd1.high, tsd1.low, tsd1.price, DATE_FORMAT(tsd1.time_time,'%Y/%m/%d %H') time_time " +
            "FROM t_stock_data tsd1 " +
            "WHERE tsd1.time_time IN ( " +
            "SELECT " +
            "MAX(tsd.time_time) maxtime " +
            "FROM " +
            "t_stock_data tsd " +
            "WHERE " +
            "1=1 AND DATE_FORMAT(tsd.time_time, '%Y/%m/%d') = DATE_FORMAT(:date1, '%Y/%m/%d') " +
            "AND (DATE_FORMAT(tsd.time_time, '%H') >= '09' " +
            "AND DATE_FORMAT(tsd.time_time, '%H') <= '11') " +
            "OR (DATE_FORMAT(tsd.time_time, '%H') >= '13'" +
            "AND DATE_FORMAT(tsd.time_time, '%H') <= '15') " +
            "GROUP BY " +
            "DATE_FORMAT(tsd.time_time, '%Y/%m/%d %H')) " +
            "ORDER BY tsd1.time_time DESC", nativeQuery = true)
    public List findSockDataHour(@Param("date1") String date);

    @Query(value = "SELECT tsd1.code, tsd1.name, tsd1.open, tsd1.yestclose, tsd1.high, tsd1.low, tsd1.price, DATE_FORMAT(tsd1.time_time,'%Y/%m/%d') time_time " +
            "FROM t_stock_data tsd1 " +
            "WHERE tsd1.time_time IN ( " +
            "SELECT " +
            "MAX(tsd.time_time) maxtime " +
            "FROM " +
            "t_stock_data tsd " +
            "WHERE " +
            "1=1 AND DATE_FORMAT(tsd.time_time, '%Y/%m/%d') <= DATE_FORMAT(:date1, '%Y/%m/%d') " +
//            "AND (DATE_FORMAT(tsd.time_time, '%Y/%m/%d %H:%i') >= '2017/09/05 09:30' " +
//            "AND DATE_FORMAT(tsd.time_time, '%Y/%m/%d %H:%i') <= '2017/09/05 11:30') " +
//            "OR (DATE_FORMAT(tsd.time_time, '%Y/%m/%d %H:%i') >= '2017/09/05 13:00'" +
//            "AND DATE_FORMAT(tsd.time_time, '%Y/%m/%d %H:%i') <= '2017/09/05 15:00') " +
            "GROUP BY " +
            "DATE_FORMAT(tsd.time_time, '%Y/%m/%d')) " +
            "ORDER BY tsd1.time_time DESC", nativeQuery = true)
    public List findSockDataDay(@Param("date1") String date);
}
