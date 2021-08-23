package com.shva.settlement.converter;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shva.settlement.dto.AtmTransactionRecordDTO;
import com.shva.settlement.model.AtmTransactionRecordEntity;

@Component("atmTransactionRecordEntitytoAtmTransactionRecordDTOConverter")
public class AtmTransactionRecordEntitytoAtmTransactionRecordDTOConverter implements Function<AtmTransactionRecordEntity, AtmTransactionRecordDTO> {

	@Autowired
	protected ModelMapper modelMapper;

	@Override
	public AtmTransactionRecordDTO apply(AtmTransactionRecordEntity atmTransactionRecordEntity) {

		if (atmTransactionRecordEntity == null) {
			return null;
		}
		AtmTransactionRecordDTO atmTransactionRecordDTO = (AtmTransactionRecordDTO) modelMapper.map(atmTransactionRecordEntity, AtmTransactionRecordDTO.class);
//		List<ImlDTO> itemList = quoteEntity.getItemList().stream().map(item -> {
//			ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class);
//			return itemDTO;
//		}).collect(Collectors.toList());
		//quoteDto.setItemList(itemList);
		return atmTransactionRecordDTO;

	}



}

