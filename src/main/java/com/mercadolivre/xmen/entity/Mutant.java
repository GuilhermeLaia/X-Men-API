package com.mercadolivre.xmen.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "MUTANT")
public class Mutant implements Serializable {

	private static final long serialVersionUID = 4339981287752058365L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
	@SequenceGenerator(name = "id_seq", sequenceName = "id_seq", allocationSize = 1)
	private Long id;
	
	@Column(name = "COUNT_MUTANT_DNA")
	private Integer countMutantDna;
	
	@Column(name = "COUNT_HUMAN_DNA")
	private Integer countHumanDna;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCountMutantDna() {
		return countMutantDna;
	}

	public void setCountMutantDna(Integer countMutantDna) {
		this.countMutantDna = countMutantDna;
	}

	public Integer getCountHumanDna() {
		return countHumanDna;
	}

	public void setCountHumanDna(Integer countHumanDna) {
		this.countHumanDna = countHumanDna;
	}

}
