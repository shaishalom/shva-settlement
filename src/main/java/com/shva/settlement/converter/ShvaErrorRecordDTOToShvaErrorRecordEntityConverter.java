package com.shva.settlement.converter;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shva.settlement.dto.ETLDTO;
import com.shva.settlement.dto.ShvaErrorRecordDTO;
import com.shva.settlement.model.ETLEntity;
import com.shva.settlement.model.ShvaErrorRecordEntity;

@Component("shvaErrorRecordDTOToShvaErrorRecordEntityConverter")
public class ShvaErrorRecordDTOToShvaErrorRecordEntityConverter implements Function<ShvaErrorRecordDTO, ShvaErrorRecordEntity> {

	@Autowired
	protected ModelMapper modelMapper;

	@Override
	public ShvaErrorRecordEntity apply(ShvaErrorRecordDTO shvaErrorRecordDTO) {

		if (shvaErrorRecordDTO == null) {
			return null;
		}
		
		ShvaErrorRecordEntity entity = (ShvaErrorRecordEntity) modelMapper.map(shvaErrorRecordDTO, ShvaErrorRecordEntity.class);
		return entity;
	}



}

