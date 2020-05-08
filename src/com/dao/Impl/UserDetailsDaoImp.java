package com.dao.Impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.UserDetailsDao;
import com.entity.Doctor;
import com.entity.User;

@Repository
public class UserDetailsDaoImp extends BaseDaoImpl<User, String> implements UserDetailsDao {

  public UserDetailsDaoImp() {
		super(User.class);
		// TODO Auto-generated constructor stub
	}
/*
@Autowired
  private SessionFactory sessionFactory;*/

  @Override
  public User findUserByUsername(String username) {
    return sessionFactory.getCurrentSession().get(User.class, username);
  }
}