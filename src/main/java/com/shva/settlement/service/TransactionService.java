package com.shva.settlement.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shva.settlement.converter.TransactionDTOtoTransactionEntityConverter;
import com.shva.settlement.converter.TransactionEntitytoTransactionDTOConverter;
import com.shva.settlement.dto.TransactionDTO;
import com.shva.settlement.model.BankEntity;
import com.shva.settlement.model.TransactionEntity;
import com.shva.settlement.repository.BankRepository;
import com.shva.settlement.repository.TransactionRepository;

/**
 * call to service that store transactions
 * @author shai
 *
 */
@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionRepository;

	@Autowired
	BankRepository bankRepository;

	@Autowired
	protected ModelMapper modelMapper;
	
	@Autowired
	@Qualifier("transactionDTOtoTransactionEntityConverter")
	TransactionDTOtoTransactionEntityConverter transactionDTOtoTransactionEntityConverter;

	@Autowired
	@Qualifier("transactionEntitytoTransactionDTOConverter")
	TransactionEntitytoTransactionDTOConverter transactionEntitytoTransactionDTOConverter;
//	
	
	
	@Autowired
	
	Logger logger;



	public List<TransactionEntity> getAll() {
		List<TransactionEntity> entityList = transactionRepository.findAll();
		return entityList;
	}

	public void save(TransactionDTO transactionDTO ) throws Exception {
		TransactionEntity transactionEntity= transactionDTOtoTransactionEntityConverter.apply(transactionDTO);
		BankEntity bankEntity = bankRepository.findById(transactionDTO.getBankId()).orElse(null); 
		transactionEntity.setBankEntity(bankEntity);
		Date now= new Date();
		transactionEntity.setCreatedDate(now);
		transactionRepository.save(transactionEntity);

	}

}	

