package com.shva.settlement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shva.settlement.model.BankTerminatorEntity;

@Repository
public interface BankTerminatorRepository
        extends JpaRepository<BankTerminatorEntity, Long> {
	
}


