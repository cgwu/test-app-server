package com.wyjf.app.domainspec;

import com.wyjf.common.domain.User;
import com.wyjf.common.domain.User_;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by Administrator on 2017/9/11.
 */
public class UserSpec {
    public static Specification<User> phoneOrNicknameContainsIgnoreCase(String searchTerm) {
        return (root, query, cb) -> {
            String containsLikePattern = getContainsLikePattern(searchTerm);
            return cb.or(
                    cb.like(cb.lower(root.<String>get(User_.phone)), containsLikePattern),
                    cb.like(cb.lower(root.<String>get(User_.nickname)), containsLikePattern)
            );
        };
    }

    private static String getContainsLikePattern(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return "%";
        }
        else {
            return "%" + searchTerm.toLowerCase() + "%";
        }
    }
}
