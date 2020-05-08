package com.dao;

import com.entity.Authorities;
import com.entity.User;

public interface UserDetailsDao extends BaseDao<User,String>{
	User findUserByUsername(String username);
}
