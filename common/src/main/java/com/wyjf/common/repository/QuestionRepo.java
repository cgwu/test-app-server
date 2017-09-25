package com.wyjf.common.repository;

import com.wyjf.common.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/9/25 0025.
 */
public interface QuestionRepo extends JpaRepository<Question, Long> {

}
