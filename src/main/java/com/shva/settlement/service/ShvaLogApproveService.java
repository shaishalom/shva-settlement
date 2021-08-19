package com.shva.settlement.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.shva.settlement.converter.ShvaLogApproveDTOToShvaLogApproveEntityConverter;
import com.shva.settlement.converter.ShvaLogApproveEntitytoShvaLogApproveDTOConverter;
import com.shva.settlement.dto.ShvaLogApproveDTO;
import com.shva.settlement.dto.StatusDTO;
import com.shva.settlement.exception.OutputStatusEnum;
import com.shva.settlement.exception.ProjBusinessException;
import com.shva.settlement.model.ShvaLogApproveEntity;
import com.shva.settlement.repository.ETLRepository;
import com.shva.settlement.repository.ShvaLogApproveRepository;

/**
 * call to service that load the shvaLogApprove
 * @author shai
 *
 */
@Service
public class ShvaLogApproveService {

	@Autowired
	ShvaLogApproveRepository shvaLogApproveRepository;



	@Autowired
	protected ModelMapper modelMapper;
	
	@Autowired
	@Qualifier("shvaLogApproveEntitytoShvaLogApproveDTOConverter")
	ShvaLogApproveEntitytoShvaLogApproveDTOConverter shvaLogApproveEntitytoShvaLogApproveDTOConverter ;

	@Autowired
	@Qualifier("shvaLogApproveDTOToShvaLogApproveEntityConverter")
	ShvaLogApproveDTOToShvaLogApproveEntityConverter shvaLogApproveDTOToShvaLogApproveEntityConverter;
	
	@Autowired
	Logger logger;


	public List<ShvaLogApproveDTO> getAllETL() {
		List<ShvaLogApproveEntity> entityList = shvaLogApproveRepository.findAll();
		List<ShvaLogApproveDTO> etlDTOList= entityList.stream().map( ent-> {
			ShvaLogApproveDTO shvaLogApproveDTO = shvaLogApproveEntitytoShvaLogApproveDTOConverter.apply(ent);
			return shvaLogApproveDTO;
		}).collect(Collectors.toList());
		return etlDTOList;
	}
	
	public ShvaLogApproveDTO getETLById(Long id) throws ProjBusinessException {
		Optional<ShvaLogApproveEntity> shvaLogApproveOptional = shvaLogApproveRepository.findById(id);

		if (shvaLogApproveOptional.isPresent()) {
			ShvaLogApproveEntity shvaLogApproveEntity = shvaLogApproveOptional.get();
			ShvaLogApproveDTO dto = shvaLogApproveEntitytoShvaLogApproveDTOConverter.apply(shvaLogApproveEntity);			
			return dto;
		} else {
			
			StatusDTO statusDTO = new StatusDTO(OutputStatusEnum.NO_RECORDS_FOUND,"No shvaLogApprove record exist for given id:" + id,"");
			logger.error(statusDTO.toString());
			throw new ProjBusinessException(statusDTO);
		}
	}
	

	public ShvaLogApproveDTO deleteShvaLogApproveById(Long id) throws ProjBusinessException {
		Optional<ShvaLogApproveEntity> shvaLogApproveEntity = shvaLogApproveRepository.findById(id);
//		if(CollectionUtils.isEmpty(imlEntityList)) {
//			logger.debug("not found for:{}",dto);
//			return null;
//		}
//		ShvaLogApproveEntity imlEntity = imlEntityList.get(0);
		ShvaLogApproveDTO dto = null;
		if (shvaLogApproveEntity!=null) {
			Long deletedId =shvaLogApproveEntity.get().getId();
			shvaLogApproveRepository.deleteById(deletedId);

			dto.setId(deletedId);
		} else {
			StatusDTO statusDTO = new StatusDTO(OutputStatusEnum.NO_RECORDS_FOUND,"No ShvaLogApprove record exist for given dto:" + dto,"");
			logger.error(statusDTO.toString());
			throw new ProjBusinessException(statusDTO);
		}
		return dto;
	}

}