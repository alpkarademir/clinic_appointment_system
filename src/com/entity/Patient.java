package com.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Entity
@Table(name = "patient")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)


public class Patient implements Serializable {
	
	@Id
    @Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "pat_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "pat_seq", sequenceName = "pat_seq", initialValue = 1, allocationSize = 1)
    private int id;
	
	@Column(name= "no")
	private int no;

	//bi-directional many-to-one association to Randevu
	@OneToMany(mappedBy="patient", fetch=FetchType.EAGER)
	private List<Randevu> randevus;

	@Column(name = "name", unique = false)
	public String name;
	
	public Patient() {
	
	}


	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}
	

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public List<Randevu> getRandevus() {
		return this.randevus;
	}

	public void setRandevus(List<Randevu> randevus) {
		this.randevus = randevus;
	}

	public Randevu addRandevus(Randevu randevus) {
		getRandevus().add(randevus);
		randevus.setPatient(this);

		return randevus;
	}

	public Randevu removeRandevus(Randevu randevus) {
		getRandevus().remove(randevus);
		randevus.setPatient(null);

		return randevus;
	}
	

}
