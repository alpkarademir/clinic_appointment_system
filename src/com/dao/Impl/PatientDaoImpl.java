package com.dao.Impl;

import org.springframework.stereotype.Repository;

import com.dao.PatientDao;
import com.entity.Patient;

@SuppressWarnings("JpaQlInspection")
@Repository
public class PatientDaoImpl extends BaseDaoImpl<Patient, Integer> implements PatientDao {

	protected PatientDaoImpl() {
		super(Patient.class);
	}

}
