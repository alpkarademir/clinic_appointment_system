package com.controller;

import javax.websocket.server.PathParam;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;  
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;  
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;

import com.dao.AuthoritiesDao;
import com.dao.BaseDao;
import com.dao.DoctorDao;
import com.dao.PatientDao;
import com.dao.RandevuDao;
import com.dao.UserDetailsDao;
import com.entity.Authorities;
import com.entity.Doctor;
import com.entity.Patient;
import com.entity.Randevu;
import com.entity.User;

import net.sf.jasperreports.engine.JRException;

@EnableWebSecurity
@Controller
@RequestMapping(value="/")
public class MainController {

	@Autowired
	private DoctorDao doctor_dao;
	
	@Autowired
	private PatientDao pat_dao;
	
	@Autowired
	private RandevuDao ran_dao;
	
	@Autowired
	private UserDetailsDao userDetailsDao;
	
	@Autowired
	private AuthoritiesDao authoritiesDao;
	
	
	@Autowired
	public MainController() {
		
	}
	
	
	@RequestMapping(value = {"/listeleme"}, method = RequestMethod.GET)
	public ModelAndView ListelePage() {
		ModelAndView model = new ModelAndView();
		model.addObject("listeleme", ran_dao.read());
		model.setViewName("listeleme");
		return model;

	}
	
	@RequestMapping(value={"/","/login"})
	public ModelAndView login() {
		ModelAndView model= new ModelAndView();
		model.addObject("login");
		model.setViewName("login");
		return model;
	}
	
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView listeleDoktor(){
		ModelAndView model=new ModelAndView();	
		model.addObject("listeled", doctor_dao.read());
		model.setViewName("index");
		return model;				
	}
	
	
	@RequestMapping(value="/sil/{id}",method=RequestMethod.GET)
	public ModelAndView silDoktor(@PathVariable int id) throws Exception{
		doctor_dao.deletePK(id);
	   return new ModelAndView("redirect:/index");						
	}
	
	@RequestMapping(value="/ekleme",method=RequestMethod.POST)
	public String ekleDoktor(@RequestParam String name, @RequestParam int no) {
		Doctor dok=new Doctor();
		dok.setName(name);
		dok.setNo(no);
		doctor_dao.create(dok);
		return("redirect:/index");
	}

	
	@RequestMapping(value="/guncelleme", method = RequestMethod.POST)
	    public String guncelleDoktor(@RequestParam  int id, @RequestParam String name, @RequestParam int no){
		Doctor dok=new Doctor();
		dok.setId(id);
		dok.setName(name);  
		dok.setNo(no);
		doctor_dao.update(dok);
		   return ("redirect:/index");
	}
	
	@RequestMapping(value="/rand", method=RequestMethod.GET)
	public ModelAndView listeleRandevu(){
		ModelAndView model=new ModelAndView();	
		model.addObject("listelex", ran_dao.read());
		model.addObject("listeled", doctor_dao.read());
		model.addObject("listeleh", pat_dao.read());
		model.setViewName("rand");
		return model;				
	}
	
	
	@RequestMapping(value="/sil3/{id}",method=RequestMethod.GET)
	public ModelAndView silRandevu(@PathVariable int id) throws Exception{
	   ran_dao.deletePK(id);
	   return new ModelAndView("redirect:/rand");						
	}
	

	@RequestMapping(value="/ekleme3",method=RequestMethod.POST)
	public String ekleRandevu(@RequestParam String time, @RequestParam int no, @RequestParam int dokid, @RequestParam int patid) {
		Randevu ran=new Randevu();
		ran.setTime(time);
		ran.setNo(no);
		ran.setDokid(dokid);
		ran.setPatid(patid);
		ran_dao.create(ran);
		return("redirect:/rand");
	}

	@RequestMapping(value="/guncelleme3", method = RequestMethod.POST)
	    public String guncelleRandevu(@RequestParam  int id, @RequestParam String time, @RequestParam int no, @RequestParam int dokid, @RequestParam int patid){
		Randevu ran=new Randevu();
		ran.setId(id);
		ran.setTime(time);  
		ran.setNo(no);
		ran.setDokid(dokid);
		ran.setPatid(patid);
		ran_dao.update(ran);
		return ("redirect:/rand");
	}
	
	@RequestMapping(value="/index2", method=RequestMethod.GET)
	public ModelAndView listelePatient(){
		ModelAndView model=new ModelAndView();	
		model.addObject("listeleh", pat_dao.read());
		model.setViewName("index2");
		return model;				
	}
	
	
	@RequestMapping(value="/sil2/{id}",method=RequestMethod.GET)
	public ModelAndView silPatient(@PathVariable int id) throws Exception{
	   pat_dao.deletePK(id);
	   return new ModelAndView("redirect:/index2");						
	}
	

	
	@RequestMapping(value="/ekleme2",method=RequestMethod.POST)
	public String eklePatient(@RequestParam String name, @RequestParam int no) {
		Patient pat=new Patient();
		pat.setName(name);
		pat.setNo(no);
		pat_dao.create(pat);
		return("redirect:/index2");
	}

	@RequestMapping(value="/guncelleme2", method = RequestMethod.POST)
	    public String guncellePatient(@RequestParam  int id, @RequestParam String name, @RequestParam int no){
		Patient pat=new Patient();
		pat.setId(id);
		pat.setName(name);  
		pat.setNo(no);
		pat_dao.update(pat);
		return ("redirect:/index2");
	}
	
	
	//--------------------------------------------------------------------------
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView model=new ModelAndView();
		model.addObject("login");
		return model;
	}
	
	
	  @GetMapping("/")
	  public String index(Model model, Principal principal) {
	    model.addAttribute("message", "You are logged in as " + principal == null ? null : principal.getName());
	    return "welcome";	
	  }
	  
		@RequestMapping(value={"/","/accessDenied"})
		public ModelAndView accessDeniedPage() {
			ModelAndView model= new ModelAndView();
			model.addObject("accessDenied");
			return model;
		}
		
		
		
//		@RequestMapping(value={"/","/register"})
//		public ModelAndView register() {
//			ModelAndView model= new ModelAndView();
//			model.addObject("register");
//			return model;
//		}
//		
//		@RequestMapping(value="/register",method=RequestMethod.POST )
//		public ModelAndView ekleDersler(@RequestParam String username,@RequestParam String password, @RequestParam String authority) {
//			  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
//	//		    System.out.print(encodedPassword);
//			    
//			ModelAndView model= new ModelAndView();
//			
//			Authorities authorities=new Authorities();
//			User user=new User();
//			
//			user.setUsername(username);
//			user.setPassword(passwordEncoder.encode(password));
//			user.setEnabled(true);
//			
//			userDetailsDao.create(user);
//			
//			authorities.setAuthority(authority);
//			authorities.setUsername(username);
//	//		authorities.setId(nextval('authorities_seq'));			
//			authoritiesDao.create(authorities);
//			model.addObject("register");
//			 return model;
//		}
	
	
		@RequestMapping(value = "/raporla", method = RequestMethod.GET)
		@ResponseBody
		public void rapor(HttpServletResponse response) throws JRException, IOException, SQLException {
			ran_dao.rapor(response);
			

			
		}
		@RequestMapping(value = "/rapor", method = RequestMethod.POST)
		@ResponseBody
		public void filtreliRapor(HttpServletResponse response, @RequestParam int id) throws JRException, IOException, SQLException {
			ran_dao.filtreliRapor(response, id);
		}
	
	
}

	

