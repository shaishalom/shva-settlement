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

	private String atm;

	private String ATM_Bank;

	private int atm_name;

	public BankTerminatorEntity() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAtm() {
		return this.atm;
	}

	public void setAtm(String atm) {
		this.atm = atm;
	}

	public String getATM_Bank() {
		return this.ATM_Bank;
	}

	public void setATM_Bank(String ATM_Bank) {
		this.ATM_Bank = ATM_Bank;
	}

	public int getAtm_name() {
		return this.atm_name;
	}

	public void setAtm_name(int atm_name) {
		this.atm_name = atm_name;
	}

}