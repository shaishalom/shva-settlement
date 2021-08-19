package com.shva.settlement.converter;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shva.settlement.dto.ShvaLogApproveDTO;
import com.shva.settlement.model.ShvaErrorRecordEntity;
import com.shva.settlement.model.ShvaLogApproveEntity;

@Component("shvaLogApproveDTOToShvaErrorRecordEntityConverter")
public class ShvaLogApproveDTOToShvaLogApproveEntityConverter implements Function<ShvaLogApproveDTO, ShvaErrorRecordEntity> {

	@Autowired
	protected ModelMapper modelMapper;

	@Override
	public ShvaErrorRecordEntity apply(ShvaLogApproveDTO shvaLogApproveDTO) {

		if (shvaLogApproveDTO == null) {
			return null;
		}
		
		ShvaErrorRecordEntity entity = (ShvaErrorRecordEntity) modelMapper.map(shvaLogApproveDTO, ShvaErrorRecordEntity.class);
		return entity;
	}



}

