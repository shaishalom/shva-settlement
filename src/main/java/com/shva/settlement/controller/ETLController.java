package com.shva.settlement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shva.settlement.dto.ETLDTO;
import com.shva.settlement.exception.ProjBusinessException;
import com.shva.settlement.service.ETLService;
import com.shva.settlement.util.StringUtils;

//import io.swagger.annotations.Api;

@RestController
@RequestMapping("/etl")
//@Api()
public class ETLController extends BaseController {
	@Autowired
	ETLService service;

	@Autowired
	Logger logger;

	@GetMapping
	public ResponseEntity<List<ETLDTO>> getAllETL() {
		List<ETLDTO> etlDtoList = service.getAllETL();

		
		String output = StringUtils.toJson(etlDtoList);
		logger.info("getAllETLs RESPONSE->" + etlDtoList);
		
		return new ResponseEntity<List<ETLDTO>>(etlDtoList, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ETLDTO> getETLById(@PathVariable("id") Long id) throws Exception {

		logger.info("getETLById REQUEST->" + id);

		
		ETLDTO etlOutputDTO = null;
		try {
			etlOutputDTO = service.getETLById(id);
		} catch (ProjBusinessException e) {

			etlOutputDTO = new ETLDTO();
			//etlOutputDTO.setStatus(handleBusinessException(e));

			return new ResponseEntity<ETLDTO>(etlOutputDTO, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		String output = StringUtils.toJson(etlOutputDTO);
		logger.info("createOrUpdateETL RESPONSE->" + output);

		return new ResponseEntity<ETLDTO>(etlOutputDTO, new HttpHeaders(), HttpStatus.OK);
	}

//	@PostMapping(value = "/createOrUpdateQuote", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<QuoteDTO> createOrUpdateQuote(@RequestBody QuoteDTO quoteInput, HttpServletRequest request)
//			throws Exception {
//
//		
//		String quoteInputStr = StringUtils.toJson(quoteInput);
//		logger.info("createOrUpdateQuote REQUEST->" + quoteInputStr);
//		
//		QuoteDTO quoteOutputDTO = null;
//		try {
//			quoteOutputDTO = service.createOrUpdateQuote(quoteInput);
//		} catch (ProjBusinessException e) {
//			quoteOutputDTO = new QuoteDTO();
//			quoteOutputDTO.setId(quoteInput.getId());
//			quoteOutputDTO.setName(quoteInput.getName());
//			
//			quoteOutputDTO.setStatus(handleBusinessException(e));
//
//			return new ResponseEntity<QuoteDTO>(quoteOutputDTO, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		String output = StringUtils.toJson(quoteOutputDTO);
//		logger.info("createOrUpdateQuote RESPONSE->" + output);
//		return new ResponseEntity<QuoteDTO>(quoteOutputDTO, new HttpHeaders(), HttpStatus.OK);
//	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ETLDTO> deleteQuoteById(@PathVariable("id") Long id) throws Exception {

		logger.info("deleteQuoteById REQUEST->" + id);

		ETLDTO etlOutputDTO = null;
		try {
			etlOutputDTO = service.deleteETLById(id);
		} catch (ProjBusinessException e) {

			etlOutputDTO = new ETLDTO();
//			etlOutputDTO.setStatus(handleBusinessException(e));

			return new ResponseEntity<ETLDTO>(etlOutputDTO, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		String output = StringUtils.toJson(etlOutputDTO);
		logger.info("deleteQuoteById RESPONSE->" + output);

		return new ResponseEntity<ETLDTO>(etlOutputDTO, new HttpHeaders(), HttpStatus.OK);
	}

}