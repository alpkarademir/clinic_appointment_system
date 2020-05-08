package com.dao.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.RandevuDao;
import com.entity.Randevu;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRProperties;

@SuppressWarnings("JpaQlInspection")
@Repository
public class RandevuDaoImpl extends BaseDaoImpl<Randevu, Integer> implements RandevuDao {

	protected RandevuDaoImpl() {
		super(Randevu.class);
	}
	
	
	@Transactional
	  @Override
		public String rapor(HttpServletResponse response) throws JRException, IOException, SQLException 
	{
			InputStream jasperStream = (InputStream) this.getClass().getResourceAsStream("/com/report/report1.jasper");
		     Map<String,Object> parameters = new HashMap<String, Object>();
		     
		     Connection c = sessionFactory.getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class).getConnection();
		     List<Randevu> masters = (List<Randevu>) sessionFactory.getCurrentSession().createCriteria(Randevu.class).list();
				
		     JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(masters);
			  
	            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
				
	            try {

//			    	   JRProperties.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
//			    	   JRProperties.setProperty("net.sf.jasperreports.default.font.name",  "Thoma");

			    	   JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, c);
					    
					     
					     System.out.println("444444444444444444444444444444444");
					     response.setContentType("application/x-pdf");
					     response.setHeader("Content-disposition", "inline; filename=report1.pdf");
					
					     final OutputStream outStream = response.getOutputStream();
					     JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
					     }catch (Exception e) {

					    System.out.println("Hata : "+e.getMessage());
					} 
					     return null;
					}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public String filtreliRapor(HttpServletResponse response, Integer id) throws JRException, IOException, SQLException {
		java.io.InputStream jasperStream = this.getClass().getResourceAsStream("/com/report/report2.jasper");

	    HashMap map = new HashMap();

	    Connection c = sessionFactory.getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class).getConnection();
	    
	    List<Randevu> masters = (List<Randevu>) sessionFactory.getCurrentSession().createCriteria(Randevu.class).list();
		
	     JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(masters);
	  
         JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		 map.put("parameter1",id);
         
         try {
	    	   JRProperties.setProperty("net.sf.jasperreports.awt.ignore.missing.font", "true");
	    	   JRProperties.setProperty("net.sf.jasperreports.default.font.name",  "Thoma");

	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, c);

	    response.setContentType("application/x-pdf");

	    response.setHeader("Content-disposition", "inline; filename=filtreliRapor1.pdf");

	    final OutputStream outStream = response.getOutputStream();

	    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

	    }catch (Exception e) {
	   	 
	   System.out.println("Hata : "+e.getMessage());

	    } 

	    return null;
	}
}
