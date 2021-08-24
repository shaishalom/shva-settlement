package com.shva.settlement.repository;

import java.util.Date;
import java.util.List;

import com.shva.settlement.model.AtmTransactionRecordEntity;


public interface AtmTransactionRecordRepositoryCustom {
	
	public List<AtmTransactionRecordEntity> findByTransactionDate(Date createdDate);
}


