package com.shva.settlement.dto;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;


/**
 * The persistent class for the shva_log_approve database table.
 * 
 */
@Data
public class ShvaLogApproveDTO implements Serializable {

	private Long id;
	private int account;
	private float amount;
	private String appRejectReason;
	private String atmNo;
	private int atmSeq;

	private int bank;

	private int branch;

	private int card;
	private String cardReader;
	private int cardType;
	private int cardholderProcessor;
	private int cpBankNum;
	private int cpProcessorNum;
	private String errorCode;
	private String f39ResponseCode;
	private int fieldNum;
	private String finishError;
	private String formatCode;
	private String i31Field55Len;
	private String i35Field55Len;
	private String ixxDate;
	private String ixxTime;
	private int originalTp;
	private String pinFailCode;
	private String pinVerificationCode;
	private String processorNumber;
	private int recType;
	private String rejReason;
	private String rejectLetter;
	private int responseCode;
	private String retainCard;
	private String scStatus;
	private String startOfMsr;
	private String t2FromChip;
	private String t2Source;
	private String terminalDate;
	private int timeOfTransaction;

	private String tp;
	private int tpBankNum;
	private int tpProcessorNum;
	private int tranType;

}