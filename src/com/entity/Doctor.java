package com.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;


@Entity
@Table(name = "doctor")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class Doctor implements Serializable{
	@Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(generator = "doctor_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "doctor_seq", sequenceName = "doctor_seq", initialValue = 1, allocationSize = 1)
    private int id;
	
	@Column(name = "name", unique = false)
	private String name;
	
	@Column(name = "no")
	private int no;
	

	//bi-directional many-to-one association to Randevu
	@OneToMany(mappedBy="doctor", fetch=FetchType.EAGER)
	private List<Randevu> randevus;

	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Doctor() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		randevus.setDoctor(this);

		return randevus;
	}

	public Randevu removeRandevus(Randevu randevus) {
		getRandevus().remove(randevus);
		randevus.setDoctor(null);

		return randevus;
	}

	
	

	
}
