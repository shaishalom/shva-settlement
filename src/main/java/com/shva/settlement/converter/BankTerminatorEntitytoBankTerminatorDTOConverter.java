package com.shva.settlement.converter;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shva.settlement.dto.BankTerminatorDTO;
import com.shva.settlement.model.BankTerminatorEntity;

@Component("bankTerminatorEntitytoBankTerminatorDTOConverter")
public class BankTerminatorEntitytoBankTerminatorDTOConverter implements Function<BankTerminatorEntity, BankTerminatorDTO> {

	@Autowired
	protected ModelMapper modelMapper;

	@Override
	public BankTerminatorDTO apply(BankTerminatorEntity bankTerminatorEntity) {

		if (bankTerminatorEntity == null) {
			return null;
		}
		BankTerminatorDTO bankTerminatorDTO = (BankTerminatorDTO) modelMapper.map(bankTerminatorEntity, BankTerminatorDTO.class);
//		bankTerminatorDTO.setBankId(bankTerminatorEntity.getId());
//		List<ImlDTO> itemList = quoteEntity.getItemList().stream().map(item -> {
//			ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class);
//			return itemDTO;
//		}).collect(Collectors.toList());
		//quoteDto.setItemList(itemList);
		return bankTerminatorDTO;

	}



}

