package com.shva.settlement.converter;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shva.settlement.dto.ShvaLogApproveDTO;
import com.shva.settlement.model.ShvaLogApproveEntity;

@Component("shvaLogApproveEntitytoShvaLogApproveDTOConverter")
public class ShvaLogApproveEntitytoShvaLogApproveDTOConverter implements Function<ShvaLogApproveEntity, ShvaLogApproveDTO> {

	@Autowired
	protected ModelMapper modelMapper;

	@Override
	public ShvaLogApproveDTO apply(ShvaLogApproveEntity shvaLogApproveEntity) {

		if (shvaLogApproveEntity == null) {
			return null;
		}
		ShvaLogApproveDTO imlDto = (ShvaLogApproveDTO) modelMapper.map(shvaLogApproveEntity, ShvaLogApproveDTO.class);
//		List<ImlDTO> itemList = quoteEntity.getItemList().stream().map(item -> {
//			ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class);
//			return itemDTO;
//		}).collect(Collectors.toList());
		//quoteDto.setItemList(itemList);
		return imlDto;

	}



}

