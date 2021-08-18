package com.shva.settlement.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the shva_error_record database table.
 * 
 */
@Entity
@Table(name="shva_error_record")
@NamedQuery(name="ShvaErrorRecord.findAll", query="SELECT s FROM ShvaErrorRecord s")
public class ShvaErrorRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;

	@Column(name="atm_number")
	private int atmNumber;

	@Column(name="atm_seq")
	private int atmSeq;

	@Column(name="error_code")
	private int errorCode;

	@Column(name="error_field")
	private String errorField;

	@Column(name="error_sequence")
	private int errorSequence;

	@Column(name="error_stage")
	private String errorStage;

	@Column(name="rec_type")
	private String recType;

	@Column(name="rejected_record")
	private String rejectedRecord;

	private int time;

	private int tp;

	@Column(name="transac_date")
	private int transacDate;

	public ShvaErrorRecord() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAtmNumber() {
		return this.atmNumber;
	}

	public void setAtmNumber(int atmNumber) {
		this.atmNumber = atmNumber;
	}

	public int getAtmSeq() {
		return this.atmSeq;
	}

	public void setAtmSeq(int atmSeq) {
		this.atmSeq = atmSeq;
	}

	public int getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorField() {
		return this.errorField;
	}

	public void setErrorField(String errorField) {
		this.errorField = errorField;
	}

	public int getErrorSequence() {
		return this.errorSequence;
	}

	public void setErrorSequence(int errorSequence) {
		this.errorSequence = errorSequence;
	}

	public String getErrorStage() {
		return this.errorStage;
	}

	public void setErrorStage(String errorStage) {
		this.errorStage = errorStage;
	}

	public String getRecType() {
		return this.recType;
	}

	public void setRecType(String recType) {
		this.recType = recType;
	}

	public String getRejectedRecord() {
		return this.rejectedRecord;
	}

	public void setRejectedRecord(String rejectedRecord) {
		this.rejectedRecord = rejectedRecord;
	}

	public int getTime() {
		return this.time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getTp() {
		return this.tp;
	}

	public void setTp(int tp) {
		this.tp = tp;
	}

	public int getTransacDate() {
		return this.transacDate;
	}

	public void setTransacDate(int transacDate) {
		this.transacDate = transacDate;
	}

}