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
            "1=1 AND DATE_FORMAT(tsd.time_time, '%Y-%m-%d') = DATE_FORMAT(:date1, '%Y-%m-%d') " +
            "AND ((DATE_FORMAT(tsd.time_time, '%H:%i') >= '09:30' " +
            "AND DATE_FORMAT(tsd.time_time, '%H:%i') <= '11:30') " +
            "OR (DATE_FORMAT(tsd.time_time, '%H:%i') >= '13:00'" +
            "AND DATE_FORMAT(tsd.time_time, '%H:%i') <= '15:00')) " +
            "GROUP BY " +
            "DATE_FORMAT(tsd.time_time, '%Y-%m-%d %H:%i')) " +
            "ORDER BY tsd1.time_time DESC", nativeQuery = true)
    public List findSockDataMin(@Param("date1") String date);

    @Query(value = "SELECT tsd1.code, tsd1.name, " +
            "(SELECT tsd.price FROM t_stock_data tsd WHERE 1=1 AND DATE_FORMAT(tsd.time_time,'%Y/%m/%d %H') = DATE_FORMAT(tsd1.time_time,'%Y/%m/%d %H') AND ((DATE_FORMAT(tsd.time_time, '%H:%i') >= '09:30' AND DATE_FORMAT(tsd.time_time, '%H:%i') <= '11:30') OR (DATE_FORMAT(tsd.time_time, '%H:%i') >= '13:00'AND DATE_FORMAT(tsd.time_time, '%H:%i') <= '15:00'))  ORDER BY tsd.time_time ASC LIMIT 0,1 ) open, " +
            "tsd1.yestclose, " +
            "MAX(tsd1.price) high , " +
            "MIN(tsd1.price) low, " +
            "tsd1.price, " +
            "DATE_FORMAT(tsd1.time_time,'%Y/%m/%d %H') time_time," +
            "(SELECT tsd.price FROM t_stock_data tsd WHERE 1=1 AND DATE_FORMAT(tsd.time_time,'%Y/%m/%d %H') = DATE_FORMAT(tsd1.time_time,'%Y/%m/%d %H') AND ((DATE_FORMAT(tsd.time_time, '%H:%i') >= '09:30' AND DATE_FORMAT(tsd.time_time, '%H:%i') <= '11:30') OR (DATE_FORMAT(tsd.time_time, '%H:%i') >= '13:00'AND DATE_FORMAT(tsd.time_time, '%H:%i') <= '15:00'))  ORDER BY tsd.time_time DESC LIMIT 0,1 ) close " +
            " " +
            "FROM t_stock_data tsd1 " +
            "WHERE 1=1 " +
            "AND (" +
            "(DATE_FORMAT(tsd1.time_time, '%H:%i') >= '09:30' AND DATE_FORMAT(tsd1.time_time, '%H:%i') <= '11:30') " +
            " OR (DATE_FORMAT(tsd1.time_time, '%H:%i') >= '13:00'AND DATE_FORMAT(tsd1.time_time, '%H:%i') <= '15:00')" +
            ") " +
            "GROUP BY DATE_FORMAT(tsd1.time_time, '%Y/%m/%d %H')" +
            "ORDER BY tsd1.time_time DESC " +
            "LIMIT 0, 40", nativeQuery = true)
    public List findSockDataHour();

    @Query(value = "SELECT tsd1.code, tsd1.name, " +
            "(SELECT tsd.price FROM t_stock_data tsd WHERE 1=1 AND DATE_FORMAT(tsd.time_time,'%Y/%m/%d') = DATE_FORMAT(tsd1.time_time,'%Y/%m/%d') AND ((DATE_FORMAT(tsd.time_time, '%H:%i') >= '09:30' AND DATE_FORMAT(tsd.time_time, '%H:%i') <= '11:30') OR (DATE_FORMAT(tsd.time_time, '%H:%i') >= '13:00'AND DATE_FORMAT(tsd.time_time, '%H:%i') <= '15:00'))  ORDER BY tsd.time_time ASC LIMIT 0,1 ) open, " +
            "tsd1.yestclose, " +
            "MAX(tsd1.price) high , " +
            "MIN(tsd1.price) low, " +
            "tsd1.price, " +
            "DATE_FORMAT(tsd1.time_time,'%Y/%m/%d') time_time," +
            "(SELECT tsd.price FROM t_stock_data tsd WHERE 1=1 AND DATE_FORMAT(tsd.time_time,'%Y/%m/%d') = DATE_FORMAT(tsd1.time_time,'%Y/%m/%d') AND ((DATE_FORMAT(tsd.time_time, '%H:%i') >= '09:30' AND DATE_FORMAT(tsd.time_time, '%H:%i') <= '11:30') OR (DATE_FORMAT(tsd.time_time, '%H:%i') >= '13:00'AND DATE_FORMAT(tsd.time_time, '%H:%i') <= '15:00'))  ORDER BY tsd.time_time DESC LIMIT 0,1 ) close " +
            " " +
            "FROM t_stock_data tsd1 " +
            "WHERE 1=1 " +
            "AND (" +
            "(DATE_FORMAT(tsd1.time_time, '%H:%i') >= '09:30' AND DATE_FORMAT(tsd1.time_time, '%H:%i') <= '11:30') " +
            " OR (DATE_FORMAT(tsd1.time_time, '%H:%i') >= '13:00'AND DATE_FORMAT(tsd1.time_time, '%H:%i') <= '15:00')" +
            ") " +
            "GROUP BY DATE_FORMAT(tsd1.time_time, '%Y/%m/%d')" +
            "ORDER BY tsd1.time_time DESC " +
            "LIMIT 0, 40", nativeQuery = true)
    public List findSockDataDay();



    @Query(value = "SELECT " +
            "tsd.price " +
            "FROM " +
            "t_stock_data tsd " +
            "WHERE " +
            "DATE_FORMAT(tsd.time_time, '%Y-%m-%d %H:%i') >= :date " +
            "AND DATE_FORMAT(tsd.time_time, '%Y-%m-%d %H:%i') <= :date " +
            "ORDER BY tsd.time_time DESC " +
            "LIMIT 0,1", nativeQuery = true)
    public Double findStockData(@Param("date")String data);

    @Query(value = "SELECT " +
            "tsd.open " +
            "FROM " +
            "t_stock_data tsd " +
            "WHERE " +
            "DATE_FORMAT(tsd.time_time, '%Y-%m-%d %H:%i') >= :date " +
            "AND DATE_FORMAT(tsd.time_time, '%Y-%m-%d %H:%i') <= :date " +
            "ORDER BY tsd.time_time DESC " +
            "LIMIT 0,1", nativeQuery = true)
    public Double findStockOpen(@Param("date")String data);
    @Query(value = "SELECT " +
            "tsd.yestclose " +
            "FROM " +
            "t_stock_data tsd " +
            "WHERE " +
            "DATE_FORMAT(tsd.time_time, '%Y-%m-%d') = :date " +
            "ORDER BY tsd.time_time DESC " +
            "LIMIT 0,1", nativeQuery = true)
    public Double findStockYestClose(@Param("date")String data);
}
