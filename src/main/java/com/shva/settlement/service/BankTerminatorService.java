package com.shva.settlement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shva.settlement.converter.BankTerminatorDTOtoBankTerminatorEntityConverter;
import com.shva.settlement.converter.BankTerminatorEntitytoBankTerminatorDTOConverter;
import com.shva.settlement.dto.BankTerminatorDTO;
import com.shva.settlement.model.BankTerminatorEntity;
import com.shva.settlement.repository.BankTerminatorRepository;

/**
 * call to service that load the shvaErrorRecord
 * @author shai
 *
 */
@Service
public class BankTerminatorService {

	@Autowired
	BankTerminatorRepository bankTerminatorRepository;



	@Autowired
	protected ModelMapper modelMapper;
	
	@Autowired
	@Qualifier("bankTerminatorEntitytoBankTerminatorDTOConverter")
	BankTerminatorEntitytoBankTerminatorDTOConverter atmTransactionRecordEntitytoAtmTransactionRecordDTOConverter ;

	@Autowired
	@Qualifier("bankTerminatorDTOtoBankTerminatorEntityConverter")
	BankTerminatorDTOtoBankTerminatorEntityConverter bankTerminatorDTOtoBankTerminatorEntityConverter;
	
	@Autowired
	Logger logger;


	public List<BankTerminatorDTO> getAll() {
		List<BankTerminatorEntity> entityList = bankTerminatorRepository.findAll();
		List<BankTerminatorDTO> etlDTOList= entityList.stream().map( ent-> {
			BankTerminatorDTO bankTerminatorDTO = atmTransactionRecordEntitytoAtmTransactionRecordDTOConverter.apply(ent);
			return bankTerminatorDTO;
		}).collect(Collectors.toList());
		return etlDTOList;
	}
	
	public List<BankTerminatorDTO> getAllSortedByIndex() {
		List<BankTerminatorEntity> entityList = bankTerminatorRepository.findAllOrderByIndex();
		List<BankTerminatorDTO> etlDTOList= entityList.stream().map( ent-> {
			BankTerminatorDTO bankTerminatorDTO = atmTransactionRecordEntitytoAtmTransactionRecordDTOConverter.apply(ent);
			return bankTerminatorDTO;
		}).collect(Collectors.toList());
		return etlDTOList;
	}

}