package com.dao.Impl;

import org.hibernate.query.Query;

import javax.persistence.NoResultException;

final class QueryResultHelper {

    private QueryResultHelper() {}

    static <T> T getSingleResultOrNull(Query<T> query) {
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}
