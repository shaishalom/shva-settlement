package com.shva.settlement.converter;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shva.settlement.dto.BankTerminatorDTO;
import com.shva.settlement.model.BankTerminatorEntity;


@Component("bankTerminatorDTOtoBankTerminatorEntityConverter")
public class BankTerminatorDTOtoBankTerminatorEntityConverter implements Function<BankTerminatorDTO, BankTerminatorEntity> {

	@Autowired
	protected ModelMapper modelMapper;

	@Override
	public BankTerminatorEntity apply(BankTerminatorDTO imlDTO) {

		if (imlDTO == null) {
			return null;
		}
		
		BankTerminatorEntity entity = (BankTerminatorEntity) modelMapper.map(imlDTO, BankTerminatorEntity.class);
		
		return entity;
	}



}

