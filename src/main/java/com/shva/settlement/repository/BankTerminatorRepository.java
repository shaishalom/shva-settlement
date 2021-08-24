package com.shva.settlement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shva.settlement.model.BankTerminatorEntity;

@Repository
public interface BankTerminatorRepository
        extends JpaRepository<BankTerminatorEntity, Long> {

	
	@Query("select a from BankTerminatorEntity a where a.active = 1 order by terminator_index")
	public List<BankTerminatorEntity> findAllOrderByIndex();
	
}


