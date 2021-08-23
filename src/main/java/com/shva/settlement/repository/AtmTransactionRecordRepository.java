package com.shva.settlement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shva.settlement.model.AtmTransactionRecordEntity;

@Repository
public interface AtmTransactionRecordRepository
        extends JpaRepository<AtmTransactionRecordEntity, Long> {
 
	
	
}


