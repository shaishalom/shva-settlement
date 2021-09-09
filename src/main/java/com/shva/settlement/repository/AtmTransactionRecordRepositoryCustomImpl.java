package com.shva.settlement.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.shva.settlement.model.AtmTransactionRecordEntity;
import com.shva.settlement.util.StringUtils;


public class AtmTransactionRecordRepositoryCustomImpl implements AtmTransactionRecordRepositoryCustom {

	@Autowired
	@Lazy
    private AtmTransactionRecordRepository atmTransactionRecordRepository;
	
	   @PersistenceContext
	    EntityManager entityManager;

	    public List<AtmTransactionRecordEntity> findByTransactionDateAsNative(Date createdDate) {
	    	
	    	TypedQuery<AtmTransactionRecordEntity> query = (TypedQuery<AtmTransactionRecordEntity>) entityManager.createNativeQuery("SELECT * FROM atm_transaction_rec WHERE transac_Date = :tran_date", AtmTransactionRecordEntity.class);
	        
	        String dateYYMMDD = StringUtils.getDateAsStringWithPattern(createdDate, "yyMMdd");
	        dateYYMMDD = "210826";
	        query.setParameter("tran_date",  dateYYMMDD);

	        List<AtmTransactionRecordEntity> atmTransactionRecordEntities= query.getResultList();
	        System.out.println("findByTransactionDateAsNative ->size:"+atmTransactionRecordEntities.size());
	        
	        List<AtmTransactionRecordEntity> eList12 = atmTransactionRecordRepository.getAtmTransactionByTerminatorBankCodeAndCreatedDate(Integer.parseInt(dateYYMMDD),"12");
	        System.out.println("size of the standart with bank 12 :"+eList12.size());
	        List<AtmTransactionRecordEntity> eList10 = atmTransactionRecordRepository.getAtmTransactionByTerminatorBankCodeAndCreatedDate(Integer.parseInt(dateYYMMDD),"10");
	        System.out.println("size of the standart with bank 10 :"+eList10.size());
	        
	        return atmTransactionRecordEntities;
	}	

	    public List<AtmTransactionRecordEntity> findByTransactionDateWithHQL(Date createdDate) {
	    	
	    	TypedQuery<AtmTransactionRecordEntity> query = entityManager.createQuery("SELECT a FROM AtmTransactionRecordEntity a " +
	                "WHERE a.transac_Date = :tran_date", AtmTransactionRecordEntity.class);
	        
	        String dateYYMMDD = StringUtils.getDateAsStringWithPattern(createdDate, "yyMMdd");
	        dateYYMMDD = "210826";
	        
	        
	        query.setParameter("tran_date", Integer.parseInt(dateYYMMDD));

	        List<AtmTransactionRecordEntity> atmTransactionRecordEntities= query.getResultList();
	        
	        System.out.println("findByTransactionDateWithHQL ->size:"+atmTransactionRecordEntities.size());
	        
	        
	        return atmTransactionRecordEntities;
	}	

}


