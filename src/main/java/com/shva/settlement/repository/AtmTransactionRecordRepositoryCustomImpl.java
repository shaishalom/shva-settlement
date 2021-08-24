package com.shva.settlement.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.shva.settlement.model.AtmTransactionRecordEntity;
import com.shva.settlement.util.StringUtils;


public class AtmTransactionRecordRepositoryCustomImpl implements AtmTransactionRecordRepositoryCustom {

	
	   @PersistenceContext
	    EntityManager entityManager;

	    public List<AtmTransactionRecordEntity> findByTransactionDate1(Date createdDate) {
	    	
	    	TypedQuery<AtmTransactionRecordEntity> query = (TypedQuery<AtmTransactionRecordEntity>) entityManager.createNativeQuery("SELECT em.* FROM atm_transaction_rec WHERE transac_Date = :date", AtmTransactionRecordEntity.class);
	        
	        String dateYYMMDD = StringUtils.getDateAsStringWithPattern(createdDate, "YYYYMMDD");
	        
	        query.setParameter("date",  dateYYMMDD);

	        List<AtmTransactionRecordEntity> atmTransactionRecordEntities= query.getResultList();
	        
	        return atmTransactionRecordEntities;
	}	

	    public List<AtmTransactionRecordEntity> findByTransactionDate(Date createdDate) {
	    	
	    	TypedQuery<AtmTransactionRecordEntity> query = entityManager.createQuery("SELECT FROM AtmTransactionRecordEntity " +
	                "WHERE transac_Date = ?", AtmTransactionRecordEntity.class);
	        
	        String dateYYMMDD = StringUtils.getDateAsStringWithPattern(createdDate, "YYYYMMDD");
	        
	        query.setParameter(1, dateYYMMDD);

	        List<AtmTransactionRecordEntity> atmTransactionRecordEntities= query.getResultList();
	        
	        return atmTransactionRecordEntities;
	}	

}


