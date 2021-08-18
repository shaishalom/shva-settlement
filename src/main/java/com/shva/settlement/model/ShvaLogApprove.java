package com.shva.settlement.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the shva_log_approve database table.
 * 
 */
@Entity
@Table(name="shva_log_approve")
@NamedQuery(name="ShvaLogApprove.findAll", query="SELECT s FROM ShvaLogApprove s")
public class ShvaLogApprove implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int account;

	private float amount;

	@Column(name="app_reject_reason")
	private String appRejectReason;

	@Column(name="atm_no")
	private String atmNo;

	@Column(name="atm_seq")
	private int atmSeq;

	private int bank;

	private int branch;

	private int card;

	@Column(name="card_reader")
	private String cardReader;

	@Column(name="card_type")
	private int cardType;

	@Column(name="cardholder_processor")
	private int cardholderProcessor;

	@Column(name="cp_bank_num")
	private int cpBankNum;

	@Column(name="cp_processor_num")
	private int cpProcessorNum;

	@Column(name="error_code")
	private String errorCode;

	@Column(name="f39_response_code")
	private String f39ResponseCode;

	@Column(name="field_num")
	private int fieldNum;

	@Column(name="finish_error")
	private String finishError;

	@Column(name="format_code")
	private String formatCode;

	@Column(name="i31_field_55_len")
	private String i31Field55Len;

	@Column(name="i35_field_55_len")
	private String i35Field55Len;

	@Column(name="ixx_date")
	private String ixxDate;

	@Column(name="ixx_time")
	private String ixxTime;

	@Column(name="original_tp")
	private int originalTp;

	@Column(name="pin_fail_code")
	private String pinFailCode;

	@Column(name="pin_verification_code")
	private String pinVerificationCode;

	@Column(name="processor_number")
	private String processorNumber;

	@Column(name="rec_type")
	private int recType;

	@Column(name="rej_reason")
	private String rejReason;

	@Column(name="reject_letter")
	private String rejectLetter;

	@Column(name="response_code")
	private int responseCode;

	@Column(name="retain_card")
	private String retainCard;

	@Column(name="sc_status")
	private String scStatus;

	@Column(name="start_of_msr")
	private String startOfMsr;

	@Column(name="t2_from_chip")
	private String t2FromChip;

	@Column(name="t2_source")
	private String t2Source;

	@Column(name="terminal_date")
	private String terminalDate;

	@Column(name="time_of_transaction")
	private int timeOfTransaction;

	private String tp;

	@Column(name="tp_bank_num")
	private int tpBankNum;

	@Column(name="tp_processor_num")
	private int tpProcessorNum;

	@Column(name="tran_type")
	private int tranType;

	public ShvaLogApprove() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccount() {
		return this.account;
	}

	public void setAccount(int account) {
		this.account = account;
	}

	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getAppRejectReason() {
		return this.appRejectReason;
	}

	public void setAppRejectReason(String appRejectReason) {
		this.appRejectReason = appRejectReason;
	}

	public String getAtmNo() {
		return this.atmNo;
	}

	public void setAtmNo(String atmNo) {
		this.atmNo = atmNo;
	}

	public int getAtmSeq() {
		return this.atmSeq;
	}

	public void setAtmSeq(int atmSeq) {
		this.atmSeq = atmSeq;
	}

	public int getBank() {
		return this.bank;
	}

	public void setBank(int bank) {
		this.bank = bank;
	}

	public int getBranch() {
		return this.branch;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}

	public int getCard() {
		return this.card;
	}

	public void setCard(int card) {
		this.card = card;
	}

	public String getCardReader() {
		return this.cardReader;
	}

	public void setCardReader(String cardReader) {
		this.cardReader = cardReader;
	}

	public int getCardType() {
		return this.cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public int getCardholderProcessor() {
		return this.cardholderProcessor;
	}

	public void setCardholderProcessor(int cardholderProcessor) {
		this.cardholderProcessor = cardholderProcessor;
	}

	public int getCpBankNum() {
		return this.cpBankNum;
	}

	public void setCpBankNum(int cpBankNum) {
		this.cpBankNum = cpBankNum;
	}

	public int getCpProcessorNum() {
		return this.cpProcessorNum;
	}

	public void setCpProcessorNum(int cpProcessorNum) {
		this.cpProcessorNum = cpProcessorNum;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getF39ResponseCode() {
		return this.f39ResponseCode;
	}

	public void setF39ResponseCode(String f39ResponseCode) {
		this.f39ResponseCode = f39ResponseCode;
	}

	public int getFieldNum() {
		return this.fieldNum;
	}

	public void setFieldNum(int fieldNum) {
		this.fieldNum = fieldNum;
	}

	public String getFinishError() {
		return this.finishError;
	}

	public void setFinishError(String finishError) {
		this.finishError = finishError;
	}

	public String getFormatCode() {
		return this.formatCode;
	}

	public void setFormatCode(String formatCode) {
		this.formatCode = formatCode;
	}

	public String getI31Field55Len() {
		return this.i31Field55Len;
	}

	public void setI31Field55Len(String i31Field55Len) {
		this.i31Field55Len = i31Field55Len;
	}

	public String getI35Field55Len() {
		return this.i35Field55Len;
	}

	public void setI35Field55Len(String i35Field55Len) {
		this.i35Field55Len = i35Field55Len;
	}

	public String getIxxDate() {
		return this.ixxDate;
	}

	public void setIxxDate(String ixxDate) {
		this.ixxDate = ixxDate;
	}

	public String getIxxTime() {
		return this.ixxTime;
	}

	public void setIxxTime(String ixxTime) {
		this.ixxTime = ixxTime;
	}

	public int getOriginalTp() {
		return this.originalTp;
	}

	public void setOriginalTp(int originalTp) {
		this.originalTp = originalTp;
	}

	public String getPinFailCode() {
		return this.pinFailCode;
	}

	public void setPinFailCode(String pinFailCode) {
		this.pinFailCode = pinFailCode;
	}

	public String getPinVerificationCode() {
		return this.pinVerificationCode;
	}

	public void setPinVerificationCode(String pinVerificationCode) {
		this.pinVerificationCode = pinVerificationCode;
	}

	public String getProcessorNumber() {
		return this.processorNumber;
	}

	public void setProcessorNumber(String processorNumber) {
		this.processorNumber = processorNumber;
	}

	public int getRecType() {
		return this.recType;
	}

	public void setRecType(int recType) {
		this.recType = recType;
	}

	public String getRejReason() {
		return this.rejReason;
	}

	public void setRejReason(String rejReason) {
		this.rejReason = rejReason;
	}

	public String getRejectLetter() {
		return this.rejectLetter;
	}

	public void setRejectLetter(String rejectLetter) {
		this.rejectLetter = rejectLetter;
	}

	public int getResponseCode() {
		return this.responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getRetainCard() {
		return this.retainCard;
	}

	public void setRetainCard(String retainCard) {
		this.retainCard = retainCard;
	}

	public String getScStatus() {
		return this.scStatus;
	}

	public void setScStatus(String scStatus) {
		this.scStatus = scStatus;
	}

	public String getStartOfMsr() {
		return this.startOfMsr;
	}

	public void setStartOfMsr(String startOfMsr) {
		this.startOfMsr = startOfMsr;
	}

	public String getT2FromChip() {
		return this.t2FromChip;
	}

	public void setT2FromChip(String t2FromChip) {
		this.t2FromChip = t2FromChip;
	}

	public String getT2Source() {
		return this.t2Source;
	}

	public void setT2Source(String t2Source) {
		this.t2Source = t2Source;
	}

	public String getTerminalDate() {
		return this.terminalDate;
	}

	public void setTerminalDate(String terminalDate) {
		this.terminalDate = terminalDate;
	}

	public int getTimeOfTransaction() {
		return this.timeOfTransaction;
	}

	public void setTimeOfTransaction(int timeOfTransaction) {
		this.timeOfTransaction = timeOfTransaction;
	}

	public String getTp() {
		return this.tp;
	}

	public void setTp(String tp) {
		this.tp = tp;
	}

	public int getTpBankNum() {
		return this.tpBankNum;
	}

	public void setTpBankNum(int tpBankNum) {
		this.tpBankNum = tpBankNum;
	}

	public int getTpProcessorNum() {
		return this.tpProcessorNum;
	}

	public void setTpProcessorNum(int tpProcessorNum) {
		this.tpProcessorNum = tpProcessorNum;
	}

	public int getTranType() {
		return this.tranType;
	}

	public void setTranType(int tranType) {
		this.tranType = tranType;
	}

}