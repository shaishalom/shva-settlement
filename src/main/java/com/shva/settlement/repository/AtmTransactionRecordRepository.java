package com.shva.settlement.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shva.settlement.model.AtmTransactionRecordEntity;
import com.shva.settlement.model.TransactionEntity;

@Repository
public interface AtmTransactionRecordRepository
        extends JpaRepository<AtmTransactionRecordEntity, Long> {
	
	@Query("select a from AtmTransactionRecordEntity a where a.transac_Date = :transacDate")
	public List<AtmTransactionRecordEntity> findByCreatedDate(@Param("transacDate") int createdDate);
 
	
	
}


