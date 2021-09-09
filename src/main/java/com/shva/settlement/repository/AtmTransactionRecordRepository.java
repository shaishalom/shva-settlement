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
        extends JpaRepository<AtmTransactionRecordEntity, Long> , AtmTransactionRecordRepositoryCustom {
	
	@Query("select a from AtmTransactionRecordEntity a where a.transac_Date = :transacDate")
	public List<AtmTransactionRecordEntity> findByCreatedDate(@Param("transacDate") int createdDate);
 
	@Query("select a from AtmTransactionRecordEntity a where a.recType='U' and a.confirm_Code=' ' and End_Action_Code=0 and a.transac_Date = :transacDate and a.ATM_Bank = :bankCode")
	public List<AtmTransactionRecordEntity> getAtmTransactionByTerminatorBankCodeAndCreatedDate(@Param("transacDate") int createdDate, @Param("bankCode") String bankCode);

//	@Query("select a from AtmTransactionRecordEntity a where a.transac_Date = :transacDate and a.ATM_Bank = :bankCode")
//	public List<AtmTransactionRecordEntity> getAtmTransactionByBankCodeAndCreatedDate(@Param("transacDate") int createdDate, @Param("bankCode") String bankCode);
	
	
	
	public interface AtmTransactionRecordRepositoryCustom {
	    public void findByTransactionDate1(Date createdDate);
	    
	    
	}	
	
}


