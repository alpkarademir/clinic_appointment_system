package com.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ManyToAny;
import org.springframework.cache.annotation.Cacheable;

@Entity
@Table(name = "randevu")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)


public class Randevu implements Serializable {
	
	@Id
    @Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "rand_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "rand_seq", sequenceName = "rand_seq", initialValue = 1, allocationSize = 1)
    private int id;
	
	@Column(name = "time", unique = false)
	private String time;
	
	@Column(name= "dokid", unique = true)
	public int dokid;
		
	@Column(name= "patid", unique = true)
	public int patid;
	
	@Column(name="no", unique=true)
	public int no;

	//bi-directional many-to-one association to Doctor
	@ManyToOne
	@JoinColumn(name="dokid", insertable=false, updatable=false)
	private Doctor doctor;

	//bi-directional many-to-one association to Patient
	@ManyToOne
	@JoinColumn(name="patid", insertable=false, updatable=false)
	private Patient patient;
	
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Randevu() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getDokid() {
		return dokid;
	}

	public void setDokid(int dokid) {
		this.dokid = dokid;
	}

	public int getPatid() {
		return patid;
	}

	public void setPatid(int patid) {
		this.patid = patid;
	}



	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	

}
