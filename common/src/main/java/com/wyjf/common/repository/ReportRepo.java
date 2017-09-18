package com.wyjf.common.repository;

import org.hibernate.SQLQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */
public class ReportRepo {

//    @PersistenceContext
//    private EntityManager em;
//
//    public List findTicketsByDrawId(long drawId, int offset, int limit){
//        Query query = em.createNativeQuery("select id, name, age from t_user");
//        query.unwrap(NativeQuery.class).setResultSetMapping().setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//        List rows = query.getResultList();
//        for (Object obj : rows) {
//            Map row = (Map) obj;
//            System.out.println("id = " + row.get("ID"));
//            System.out.println("name = " + row.get("NAME"));
//            System.out.println("age = " + row.get("AGE"));
//        }
//    }
}
