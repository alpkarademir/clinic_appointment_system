package com.dao;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;


@Transactional
public interface BaseDao<T extends Serializable, PK extends Serializable> {
	 /** Persist the newInstance object into database */
     PK create(T newInstance);

     /**
      * Retrieve an object that was previously persisted to the database using
      * the indicated id as primary key
      */
     T read(PK id);
     List<T> read();

     /** Save changes made to a persistent object. */
     void update(T transientObject);

     /** Remove an object from persistent storage in the database */
     void deletePK(PK id) throws Exception;
     void delete(T persistentObject) throws Exception;
	
}
