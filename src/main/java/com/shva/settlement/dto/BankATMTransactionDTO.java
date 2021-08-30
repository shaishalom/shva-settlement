package com.shva.settlement.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankATMTransactionDTO {
	private List<AtmTransactionRecordDTO> atmTransactionRecordDTOList;
	private Integer date;
	private String bankCode;

	
}
