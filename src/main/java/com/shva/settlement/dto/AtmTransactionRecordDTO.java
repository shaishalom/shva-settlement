package com.shva.settlement.dto;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;


@Data
public class AtmTransactionRecordDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private int account;

	private int account_Type;

	private int amount;

	private int atm;

	private String atm_Address;

	private String ATM_Bank;

	private int atm_Branch;

	private int atm_Mikud;

	private int atm_Number;

	private int atm_Seq;

	private int auth_Num;

	private int authentication_code;

	private int BOI_issuer_id;

	private int branch;

	private int card;

	private String card_Reader;

	private int card_Retain_Reas;

	private int card_Type;

	private int commission;

	private String confirm_Code;

	private int connect_Code;

	private int cp;

	private int currency;

	private int end_Action_Code;

	private int estimat_Time_Sign;

	private String event;

	private String event_Status;

	private String flag_New;

	private String issField;

	private int issuer_id;

	private String panseq;

	private String panseq_Status;

	private int pin_Verificationcode;

	@Column(name="rec_type")
	private String recType;

	private int reply_Code;

	private String sc_Status;

	private String settlement_Flag;

	private String settlement_info;

	private int spec_Service;

	private String t2_Source;

	private int time;

	private int tp;

	private String track_2_Data;

	private int transac_Date;

	private int transac_Date_New;

	private int transm_Date;


}