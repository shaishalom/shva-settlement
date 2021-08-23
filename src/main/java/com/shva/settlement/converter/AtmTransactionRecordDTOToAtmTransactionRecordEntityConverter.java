package com.shva.settlement.converter;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shva.settlement.dto.AtmTransactionRecordDTO;
import com.shva.settlement.dto.ETLDTO;
import com.shva.settlement.dto.ShvaErrorRecordDTO;
import com.shva.settlement.model.AtmTransactionRecordEntity;
import com.shva.settlement.model.ETLEntity;
import com.shva.settlement.model.ShvaErrorRecordEntity;

@Component("atmTransactionRecordDTOToAtmTransactionRecordEntityConverter")
public class AtmTransactionRecordDTOToAtmTransactionRecordEntityConverter implements Function<AtmTransactionRecordDTO, AtmTransactionRecordEntity> {

	@Autowired
	protected ModelMapper modelMapper;

	@Override
	public AtmTransactionRecordEntity apply(AtmTransactionRecordDTO shvaErrorRecordDTO) {

		if (shvaErrorRecordDTO == null) {
			return null;
		}
		
		AtmTransactionRecordEntity entity = (AtmTransactionRecordEntity) modelMapper.map(shvaErrorRecordDTO, AtmTransactionRecordEntity.class);
		return entity;
	}



}

