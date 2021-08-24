package com.shva.settlement.task;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.shva.settlement.dto.AtmTransactionRecordDTO;
import com.shva.settlement.dto.BankTerminatorDTO;
import com.shva.settlement.dto.ETLDTO;
import com.shva.settlement.dto.InputFileDTO;
import com.shva.settlement.service.AggregateService;
import com.shva.settlement.service.AtmTransactionService;
import com.shva.settlement.service.BankTerminatorService;
import com.shva.settlement.service.ETLService;
import com.shva.settlement.service.ReadFileService;
import com.shva.settlement.service.WriteOutputService;
import com.shva.settlement.util.StringUtils;

@Component
public class ScheduledTasks {
	
	@Autowired
    private Logger logger;
	
	
	@Autowired
	private Environment env;

	@Autowired
	AggregateService aggregateService;

	@Autowired
	ETLService etlService;
	
	@Autowired
	ReadFileService readFileService;

	@Autowired
	AtmTransactionService atmTransactionService;
	
	@Autowired
	WriteOutputService writeOutputService;
	
	@Autowired
	BankTerminatorService bankTerminatorService;
	


    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void scheduleTaskWithFixedRate() {}

    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    
	public void handleATMTransactions(ETLDTO etlDTO) {

		
		List<BankTerminatorDTO> bankTerminatorDTOList= bankTerminatorService.getAllSortedByIndex();
		bankTerminatorDTOList.stream().peek(System.out::println);
		bankTerminatorDTOList.stream().forEach( dto->{
			System.out.println(dto);
		});
		
		String lineType = env.getProperty("lineType");
		Date currentDate = new Date();
		String currentDateStr = StringUtils.getDateAsStringWithPattern(currentDate,"YYMMDD");
		int currentDateInt = Integer.parseInt(currentDateStr);
		
		List<AtmTransactionRecordDTO> atmTransactionRecordDTOs = atmTransactionService.getAtmTransactionByCreatedDate(currentDateInt);
		logger.info("num of ATM transaction for:{} ,Fetched:{}",currentDateInt,atmTransactionRecordDTOs.size());
		
//		aggregateService.aggregatelineInInputFiles(inputFileList, lineType);
//		try {
//			writeOutputService.writeOutput(inputFileList,etlDTO);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			logger.error("failed in write to file", e);
//			e.printStackTrace();
//		}

	}
    

	
	public void runETLTransactionOld(ETLDTO etlDTO) {

		String lineType = env.getProperty("lineType");

		List<InputFileDTO> inputFileList = readFileService.readInputFile(etlDTO.getFolder());
		
		aggregateService.aggregatelineInInputFiles(inputFileList, lineType);
		try {
			writeOutputService.writeOutput(inputFileList,etlDTO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("failed in write to file", e);
			e.printStackTrace();
		}

	}
	
	
	public void runETLTransaction() {

		String sourceInputPath = env.getProperty("inputFolder");
		String lineType = env.getProperty("lineType");
		ETLDTO firstEtlDto = etlService.getAllETL().get(0);
		List<InputFileDTO> inputFileList = readFileService.readInputFile(sourceInputPath);
		
		aggregateService.aggregatelineInInputFiles(inputFileList, lineType);
		try {
			writeOutputService.writeOutput(inputFileList,firstEtlDto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("failed in write to file", e);
			e.printStackTrace();
		}

	}

	public void invokeSchedule(List<ETLDTO> etoList) {
		
	}


}
