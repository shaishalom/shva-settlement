package com.shva.settlement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shva.settlement.model.ShvaLogApproveEntity;
 
@Repository
public interface ShvaLogApproveRepository
        extends JpaRepository<ShvaLogApproveEntity, Long> {
 
	
	
}
