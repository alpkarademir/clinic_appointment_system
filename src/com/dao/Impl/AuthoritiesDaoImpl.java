package com.dao.Impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AuthoritiesDao;
import com.dao.DoctorDao;
import com.entity.Authorities;
import com.entity.Doctor;
import com.entity.User;

@Repository
public class AuthoritiesDaoImpl extends BaseDaoImpl<Authorities, Integer> implements AuthoritiesDao  {
	
	 public AuthoritiesDaoImpl() {
		super(Authorities.class);
		// TODO Auto-generated constructor stub
	}

	/*@Autowired
	 private SessionFactory sessionFactory;*/

	@Override
	public List findAuthorityByName(String username) {
		
		String hql = "select authority from Authorities  where username = :username";
		List result = sessionFactory.getCurrentSession().createQuery(hql)
		.setParameter("username", username)
		.list();
		
		return result;
	/*	sessionFactory.getCurrentSession().createQuery(" authority from authorities WHERE username='admin' ").getSingleResult();
		System.out.println(sessionFactory.getCurrentSession().createQuery(" authority from authorities WHERE username='admin' ").getSingleResult().toString()+"----------------------");
	return	sessionFactory.getCurrentSession().createQuery(" authority from authorities WHERE username='admin' ").getSingleResult().toString();
     */    
		// return (sessionFactory.getCurrentSession().get(Authorities.class, username)).toString();
	}



//	@Override
//	@SuppressWarnings("unchecked")
//    @Transactional(readOnly = true)
//    public List<Authorities> read() {
//		
//		 return sessionFactory.getCurrentSession().createQuery("FROM " +  Authorities.class)
//	                .getResultList();
//    }

	 
}
