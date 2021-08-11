package com.shva.settlement.converter;

import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shva.settlement.dto.ETLDTO;
import com.shva.settlement.dto.TransactionDTO;
import com.shva.settlement.model.ETLEntity;
import com.shva.settlement.model.TransactionEntity;

@Component("transactionDTOtoTransactionEntityConverter")
public class TransactionDTOtoTransactionEntityConverter implements Function<TransactionDTO, TransactionEntity> {

	@Autowired
	protected ModelMapper modelMapper;

	@Override
	public TransactionEntity apply(TransactionDTO transactionDTO) {

		if (transactionDTO == null) {
			return null;
		}
		
		TransactionEntity entity = (TransactionEntity) modelMapper.map(transactionDTO, TransactionEntity.class);
		return entity;
	}



}

