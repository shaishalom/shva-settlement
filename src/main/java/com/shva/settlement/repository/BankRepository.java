package com.shva.settlement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shva.settlement.model.BankEntity;
 
@Repository
public interface BankRepository
        extends JpaRepository<BankEntity, Long> {
 
	
	
}
