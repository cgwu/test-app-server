package com.wyjf.common.repository;

import com.wyjf.common.domain.LogBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */
public interface LogBalanceRepo extends JpaRepository<LogBalance, Long> {

    public List<LogBalance> findByUidOrderByLogTimeDesc(long uid);

    public List<LogBalance> findByUidAndTypeOrderByLogTimeDesc(long uid, int type);

}
