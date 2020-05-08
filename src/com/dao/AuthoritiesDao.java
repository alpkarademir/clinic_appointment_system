package com.dao;

import java.util.List;

import com.entity.Authorities;


public interface AuthoritiesDao extends BaseDao<Authorities,Integer>{
	
	List findAuthorityByName(String username);
//	 List<Authorities> read();

}
