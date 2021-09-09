package com.shva.settlement.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import lombok.Data;


/**
 * The persistent class for the bank_terminator database table.
 * 
 */
@Component
@Data
public class BankTerminatorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;

	private Integer terminator_index;

	private String name;

	private String bank_code;
	
	private Integer active;
	
	private Long totalSummaryAmount=null;
	
	private List<AtmTransactionRecordDTO>  atmTransactionRecordList;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BankTerminatorDTO [id=");
		builder.append(id);
		builder.append(", terminator_index=");
		builder.append(terminator_index);
		builder.append(", name=");
		builder.append(name);
		builder.append(", bank_code=");
		builder.append(bank_code);
		builder.append(", active=");
		builder.append(active);
		builder.append(", totalSummaryAmount=");
		builder.append(totalSummaryAmount);
		builder.append("]");
		return builder.toString();
	}
	


}