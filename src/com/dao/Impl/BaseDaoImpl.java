package com.dao.Impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BaseDao;
import com.entity.Doctor;



@SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection", "SpringJavaInjectionPointsAutowiringInspection", "WeakerAccess"})
@Repository
public abstract class BaseDaoImpl<T extends Serializable,PK extends Serializable> implements BaseDao<T,PK>{

	private Class<T> type;

	  @Autowired
	    public SessionFactory sessionFactory;

	    public SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

	    public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }

	    public BaseDaoImpl(Class<T> type) {
	        this.type = type;
	    }

	    // Not showing implementations of getSession() and setSessionFactory()
	    private Session getSession() {
	        Session session = sessionFactory.getCurrentSession();
	        return session;
	    }

	    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	    public PK create(T o) {
	        return (PK) getSession().save(o);
	    }

	    @Transactional(readOnly = false, rollbackFor = RuntimeException.class, propagation=Propagation.REQUIRED)
	    public void update(T o) {
	        getSession().update(o);
	    }

	    @Transactional(readOnly = true)
	    public T read(PK id) {
	        return (T) getSession().get(type, id);
	    }

	    @SuppressWarnings("unchecked")
	    @Transactional(readOnly = true)
	    public List<T> read() {
	      Criteria criteria = null;
			  return (List<T>) getSession().createCriteria(type).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	    //	 return (List<T>) criteria.setProjection(Projections.distinct(Projections.property("ogr_id")));
	    }

	    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	    public void deletePK(PK id) {
	        T o = getSession().load(type, id);
	        getSession().delete(o);
	    }

	    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	    public void delete(T o) {
	        getSession().delete(o);
	    }

	


	


	 
	 
}
