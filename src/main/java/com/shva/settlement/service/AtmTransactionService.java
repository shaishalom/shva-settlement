package com.shva.settlement.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shva.settlement.converter.AtmTransactionRecordDTOToAtmTransactionRecordEntityConverter;
import com.shva.settlement.converter.AtmTransactionRecordEntitytoAtmTransactionRecordDTOConverter;
import com.shva.settlement.dto.AtmTransactionRecordDTO;
import com.shva.settlement.dto.StatusDTO;
import com.shva.settlement.exception.OutputStatusEnum;
import com.shva.settlement.exception.ProjBusinessException;
import com.shva.settlement.model.AtmTransactionRecordEntity;
import com.shva.settlement.repository.AtmTransactionRecordRepository;

/**
 * call to service that load the shvaErrorRecord
 * @author shai
 *
 */
@Service
public class AtmTransactionService {

	@Autowired
	AtmTransactionRecordRepository atmTransactionRecordRepository;



	@Autowired
	protected ModelMapper modelMapper;
	
	@Autowired
	@Qualifier("atmTransactionRecordEntitytoAtmTransactionRecordDTOConverter")
	AtmTransactionRecordEntitytoAtmTransactionRecordDTOConverter atmTransactionRecordEntitytoAtmTransactionRecordDTOConverter ;

	@Autowired
	@Qualifier("atmTransactionRecordDTOToAtmTransactionRecordEntityConverter")
	AtmTransactionRecordDTOToAtmTransactionRecordEntityConverter atmTransactionRecordDTOToAtmTransactionRecordEntityConverter;
	
	@Autowired
	Logger logger;


	public List<AtmTransactionRecordDTO> getAtmTransactionByCreatedDate(Integer createdDate) {

		//just for demo - invoke customize queries	
		atmTransactionRecordRepository.findByTransactionDateAsNative(new Date());
		atmTransactionRecordRepository.findByTransactionDateWithHQL(new Date());
		
		
		List<AtmTransactionRecordEntity> entityList = atmTransactionRecordRepository.findByCreatedDate(createdDate);
		List<AtmTransactionRecordDTO> atmTransactionRecordDTOs= entityList.stream().map( ent-> {
			AtmTransactionRecordDTO atmTransactionRecordDTO = atmTransactionRecordEntitytoAtmTransactionRecordDTOConverter.apply(ent);
			return atmTransactionRecordDTO;
		}).collect(Collectors.toList());
		return atmTransactionRecordDTOs;
	}

	public List<AtmTransactionRecordDTO> getAtmTransactionByCreatedDateAndTerminatorBankCode(Integer createdDate,String bankCode) {
		List<AtmTransactionRecordEntity> entityList = atmTransactionRecordRepository.getAtmTransactionByTerminatorBankCodeAndCreatedDate(createdDate,bankCode);
		logger.info("for Bank(Terminator):{} and date:{} -> fetch num of records:{}",bankCode,createdDate,entityList.size());
		List<AtmTransactionRecordDTO> atmTransactionRecordDTOs= entityList.stream().map( ent-> {
			AtmTransactionRecordDTO atmTransactionRecordDTO = atmTransactionRecordEntitytoAtmTransactionRecordDTOConverter.apply(ent);
			return atmTransactionRecordDTO;
		}).collect(Collectors.toList());
		return atmTransactionRecordDTOs;
	}
	
	
	public AtmTransactionRecordDTO getETLById(Long id) throws ProjBusinessException {
		Optional<AtmTransactionRecordEntity> atmTransactionRecordOptional = atmTransactionRecordRepository.findById(id);

		if (atmTransactionRecordOptional.isPresent()) {
			AtmTransactionRecordEntity atmTransactionRecordEntity = atmTransactionRecordOptional.get();
			AtmTransactionRecordDTO dto = atmTransactionRecordEntitytoAtmTransactionRecordDTOConverter.apply(atmTransactionRecordEntity);			
			return dto;
		} else {
			
			StatusDTO statusDTO = new StatusDTO(OutputStatusEnum.NO_RECORDS_FOUND,"No shvaErrorRecord record exist for given id:" + id,"");
			logger.error(statusDTO.toString());
			throw new ProjBusinessException(statusDTO);
		}
	}
	

	public AtmTransactionRecordDTO deleteShvaErrorRecordById(Long id) throws ProjBusinessException {
		Optional<AtmTransactionRecordEntity> atmTransactionRecordEntity = atmTransactionRecordRepository.findById(id);
//		if(CollectionUtils.isEmpty(imlEntityList)) {
//			logger.debug("not found for:{}",dto);
//			return null;
//		}
//		AtmTransactionRecordEntity imlEntity = imlEntityList.get(0);
		AtmTransactionRecordDTO dto = null;
		if (atmTransactionRecordEntity!=null) {
			Long deletedId =atmTransactionRecordEntity.get().getId();
			atmTransactionRecordRepository.deleteById(deletedId);

			dto.setId(deletedId);
		} else {
			StatusDTO statusDTO = new StatusDTO(OutputStatusEnum.NO_RECORDS_FOUND,"No ShvaErrorRecord record exist for given dto:" + dto,"");
			logger.error(statusDTO.toString());
			throw new ProjBusinessException(statusDTO);
		}
		return dto;
	}

}