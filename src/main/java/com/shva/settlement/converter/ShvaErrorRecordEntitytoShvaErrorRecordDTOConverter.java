package com.shva.settlement.converter;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shva.settlement.dto.ETLDTO;
import com.shva.settlement.dto.ShvaErrorRecordDTO;
import com.shva.settlement.model.ShvaErrorRecordEntity;

@Component("shvaErrorRecordEntitytoShvaErrorRecordDTOConverter")
public class ShvaErrorRecordEntitytoShvaErrorRecordDTOConverter implements Function<ShvaErrorRecordEntity, ShvaErrorRecordDTO> {

	@Autowired
	protected ModelMapper modelMapper;

	@Override
	public ShvaErrorRecordDTO apply(ShvaErrorRecordEntity shvaErrorRecordEntity) {

		if (shvaErrorRecordEntity == null) {
			return null;
		}
		ShvaErrorRecordDTO shErrorRecordDTO = (ShvaErrorRecordDTO) modelMapper.map(shvaErrorRecordEntity, ShvaErrorRecordDTO.class);
//		List<ImlDTO> itemList = quoteEntity.getItemList().stream().map(item -> {
//			ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class);
//			return itemDTO;
//		}).collect(Collectors.toList());
		//quoteDto.setItemList(itemList);
		return shErrorRecordDTO;

	}



}

