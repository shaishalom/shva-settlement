package com.shva.settlement.dto;

import java.io.Serializable;

/**
 * The persistent class for the shva_error_record database table.
 * 
 */
import lombok.Data;

@Data
public class ShvaErrorRecordDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private int atmNumber;
	private int atmSeq;
	private int errorCode;
	private String errorField;
	private int errorSequence;
	private String errorStage;
	private String recType;
	private String rejectedRecord;

	private int time;

	private int tp;
	private int transacDate;


}