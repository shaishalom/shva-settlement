package com.shva.settlement.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shva.settlement.converter.ShvaErrorRecordDTOToShvaErrorRecordEntityConverter;
import com.shva.settlement.converter.ShvaErrorRecordEntitytoShvaErrorRecordDTOConverter;
import com.shva.settlement.dto.ShvaErrorRecordDTO;
import com.shva.settlement.dto.StatusDTO;
import com.shva.settlement.exception.OutputStatusEnum;
import com.shva.settlement.exception.ProjBusinessException;
import com.shva.settlement.model.ShvaErrorRecordEntity;
import com.shva.settlement.repository.ShvaErrorRecordRepository;

/**
 * call to service that load the shvaErrorRecord
 * @author shai
 *
 */
@Service
public class ShvaErrorRecordService {

	@Autowired
	ShvaErrorRecordRepository shvaErrorRecordRepository;



	@Autowired
	protected ModelMapper modelMapper;
	
	@Autowired
	@Qualifier("shvaErrorRecordEntitytoShvaErrorRecordDTOConverter")
	ShvaErrorRecordEntitytoShvaErrorRecordDTOConverter shvaErrorRecordEntitytoShvaErrorRecordDTOConverter ;

	@Autowired
	@Qualifier("shvaErrorRecordDTOToShvaErrorRecordEntityConverter")
	ShvaErrorRecordDTOToShvaErrorRecordEntityConverter shvaErrorRecordDTOToShvaErrorRecordEntityConverter;
	
	@Autowired
	Logger logger;


//	public List<ShvaErrorRecordDTO> getAllETL() {
//		List<ShvaErrorRecordEntity> entityList = shvaErrorRecordRepository.findAll();
//		List<ShvaErrorRecordDTO> etlDTOList= entityList.stream().map( ent-> {
//			ShvaErrorRecordDTO shvaErrorRecordDTO = shvaErrorRecordEntitytoShvaErrorRecordDTOConverter.apply(ent);
//			return shvaErrorRecordDTO;
//		}).collect(Collectors.toList());
//		return etlDTOList;
//	}
	
	public ShvaErrorRecordDTO getETLById(Long id) throws ProjBusinessException {
		Optional<ShvaErrorRecordEntity> shvaErrorRecordOptional = shvaErrorRecordRepository.findById(id);

		if (shvaErrorRecordOptional.isPresent()) {
			ShvaErrorRecordEntity shvaErrorRecordEntity = shvaErrorRecordOptional.get();
			ShvaErrorRecordDTO dto = shvaErrorRecordEntitytoShvaErrorRecordDTOConverter.apply(shvaErrorRecordEntity);			
			return dto;
		} else {
			
			StatusDTO statusDTO = new StatusDTO(OutputStatusEnum.NO_RECORDS_FOUND,"No shvaErrorRecord record exist for given id:" + id,"");
			logger.error(statusDTO.toString());
			throw new ProjBusinessException(statusDTO);
		}
	}
	

	public ShvaErrorRecordDTO deleteShvaErrorRecordById(Long id) throws ProjBusinessException {
		Optional<ShvaErrorRecordEntity> shvaErrorRecordEntity = shvaErrorRecordRepository.findById(id);
//		if(CollectionUtils.isEmpty(imlEntityList)) {
//			logger.debug("not found for:{}",dto);
//			return null;
//		}
//		ShvaErrorRecordEntity imlEntity = imlEntityList.get(0);
		ShvaErrorRecordDTO dto = null;
		if (shvaErrorRecordEntity!=null) {
			Long deletedId =shvaErrorRecordEntity.get().getId();
			shvaErrorRecordRepository.deleteById(deletedId);

			dto.setId(deletedId);
		} else {
			StatusDTO statusDTO = new StatusDTO(OutputStatusEnum.NO_RECORDS_FOUND,"No ShvaErrorRecord record exist for given dto:" + dto,"");
			logger.error(statusDTO.toString());
			throw new ProjBusinessException(statusDTO);
		}
		return dto;
	}

}