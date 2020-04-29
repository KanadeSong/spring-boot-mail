package com.ljj.mail.common.dynamicquery.impl;

import com.ljj.mail.common.dynamicquery.DynamicQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * <p>
 * DynamicQueryImpl 动态jpql/nativesql查询的实现类
 * </p>
 *
 * @author LeeJack
 * @date Created in 2020/4/29 16:04
 */
@Repository
@Slf4j
public class DynamicQueryImpl implements DynamicQuery {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void save(Object entity) {
        em.persist(entity);
    }

    @Override
    public void update(Object entity) {
        em.merge(entity);
    }

    @Override
    public <T> void delete(Class<T> entityClass, Object entityid) {
        delete(entityClass, new Object[]{entityid});
    }

    @Override
    public <T> void delete(Class<T> entityClass, Object[] entityids) {
        for (Object id : entityids) {
            em.remove(em.getReference(entityClass, id));
        }
    }

    @Override
    public Long nativeQueryCount(String nativeSql, Object... params) {
        Object count =
                createNativeQuery(nativeSql, params).getSingleResult();
        return ((Number) count).longValue();
    }

    private Query createNativeQuery(String sql, Object[] params) {
        Query q = em.createNativeQuery(sql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                //与Hiberante不同,jpa query从位置1开始
                q.setParameter(i + 1, params[i]);
            }
        }
        return q;
    }
}
