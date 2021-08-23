package com.shva.settlement.dto;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the bank_terminator database table.
 * 
 */
@Data
public class BankTerminatorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private String atm;

	private String ATM_Bank;

	private int atm_name;


}