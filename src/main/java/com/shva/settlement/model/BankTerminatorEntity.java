package com.shva.settlement.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bank_terminator database table.
 * 
 */
@Entity
@Table(name="bank_terminator")
@NamedQuery(name="BankTerminatorEntity.findAll", query="SELECT b FROM BankTerminatorEntity b")
public class BankTerminatorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	private Integer terminator_index;

	private String name;

	private String bank_code;

	private Integer active;
	

	public BankTerminatorEntity() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public void setAtm(Integer terminator_index) {
		this.terminator_index = terminator_index;
	}


	public String getBank_code() {
		return bank_code;
	}

	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTerminator_index() {
		return terminator_index;
	}

	public void setTerminator_index(Integer terminator_index) {
		this.terminator_index = terminator_index;
	}

}