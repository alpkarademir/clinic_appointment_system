package com.dao;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import com.entity.Randevu;

import net.sf.jasperreports.engine.JRException;

public interface RandevuDao extends BaseDao<Randevu, Integer>{

	String rapor(HttpServletResponse response) throws JRException, IOException, SQLException;


	String filtreliRapor(HttpServletResponse response, Integer id) throws JRException, IOException, SQLException;
	
}
