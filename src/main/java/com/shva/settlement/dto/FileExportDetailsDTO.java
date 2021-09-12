package com.shva.settlement.dto;

import lombok.Data;

@Data
public class FileExportDetailsDTO {

	private String folderName;
	private String prefixName;
	private String sourceBank;
	private String destinationBank;
	private String dateYYMMDD;
	private String timeHHMMSS;
	
	public String generateFileName() {
		StringBuilder sb = new StringBuilder(prefixName);
		sb.append("_").append(sourceBank);
		sb.append("_").append(destinationBank);
		sb.append("_").append(dateYYMMDD);
		sb.append("_").append(timeHHMMSS);
		
		return sb.toString();
		
	}
}
