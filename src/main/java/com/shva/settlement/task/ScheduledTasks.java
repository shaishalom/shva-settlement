package com.shva.settlement.task;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.core.env.Environment;
import org.springframework.core.task.support.ExecutorServiceAdapter;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.shva.settlement.dto.AtmTransactionRecordDTO;
import com.shva.settlement.dto.BankATMTransactionDTO;
import com.shva.settlement.dto.BankTerminatorDTO;
import com.shva.settlement.dto.ETLDTO;
import com.shva.settlement.dto.InputFileDTO;
import com.shva.settlement.service.AggregateService;
import com.shva.settlement.service.AtmTransactionService;
import com.shva.settlement.service.BankTerminatorService;
import com.shva.settlement.service.ETLService;
import com.shva.settlement.service.ReadFileService;
import com.shva.settlement.service.WriteOutputService;
import com.shva.settlement.task.objects.BaseBoRES;
import com.shva.settlement.task.objects.BaseTask;
import com.shva.settlement.task.objects.BaseTaskRes;
import com.shva.settlement.task.objects.SRQOutput;
import com.shva.settlement.task.objects.TaskImpl;
import com.shva.settlement.task.objects.TaskReq;
import com.shva.settlement.task.objects.TaskRes;
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
	
//	@Autowired
	ExecutorServiceAdapter executorServiceAdapter;
	
    @Autowired
    ModelMapper modelMapper;



    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void scheduleTaskWithFixedRate() {}

    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    
	@Lookup
	public TaskImpl getTaskImpl() {
		return null;
	}
	
//	@LogTracer
//	public SRQ166Output srq166(SRQ166Input srq166Input) {
//		return (SRQ166Output) super.executeBusiness(srq166Input);
//	}
	
    
	
    private void fillBankTerminatorRecords(List<BankTerminatorDTO> bankTerminatorDTOList) {
		Runnable runnableTask = () -> {
		    try {
		        TimeUnit.MILLISECONDS.sleep(300);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		};
	
			
		Callable<String> callableTask = () -> {
		    TimeUnit.MILLISECONDS.sleep(300);
		    System.out.println("call callableTask");
		    return "Task's execution";
		};
	
		
		ExecutorService executorService = 
				  new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,   
				  new LinkedBlockingQueue<Runnable>());		

		List<Callable<BankATMTransactionDTO>> callableTasks = bankTerminatorDTOList.stream().map( dto-> {
			BankTerminatorCallable taskCallable = new BankTerminatorCallable(dto,atmTransactionService);
			return taskCallable;
		}).collect(Collectors.toList());
		
		
//		List<Callable<String>> callableTasks = new ArrayList<>();
//		BankTerminatorCallable taskCallableA = new BankTerminatorCallable("callableA"); 
//		callableTasks.add(taskCallableA);
//		BankTerminatorCallable taskCallableB = new BankTerminatorCallable("callableB"); 
//		callableTasks.add(taskCallableB);
//		BankTerminatorCallable taskCallableC = new BankTerminatorCallable("callableC"); 
//		callableTasks.add(taskCallableC);
		
//		callableTasks.add(callableTask);
//		callableTasks.add(callableTask);
//		callableTasks.add(callableTask);
		List<Future<BankATMTransactionDTO>> futures = null;
		try {
			futures = executorService.invokeAll(callableTasks);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<BankATMTransactionDTO> bankAtmTransactionList = futures.stream().map(future -> {
			try {
				return future.get();
			} catch (Exception e) {
				// createErrorResponse(baseBoREQ, baseSRQRES, ESRQOutputStatus.UNEXPECTED);
				e.printStackTrace();
				return null;
			}

		}).collect(Collectors.toList());
		
		//bankTerminatorDTOList
		
		bankAtmTransactionList.stream().forEach(bankATMTransactionDto -> {
			Long totalAmountOfBank =bankATMTransactionDto.getAtmTransactionRecordDTOList().stream().mapToLong(AtmTransactionRecordDTO::getAmount).sum();
			
			BankTerminatorDTO foundBankTerminatorDTO = bankTerminatorDTOList.stream().filter(bankTerminatorDTO -> {
					if (bankTerminatorDTO.getBank_code().equals(bankATMTransactionDto.getBankCode())){
						return true;
					}
					return false;
			}).findFirst().orElseGet(null);
			
							
			if (foundBankTerminatorDTO!=null) {
				foundBankTerminatorDTO.setTotalSummaryAmount(totalAmountOfBank);
				foundBankTerminatorDTO.setAtmTransactionRecordList(bankATMTransactionDto.getAtmTransactionRecordDTOList());
			}
							
			logger.info("For terminator bank:"+bankATMTransactionDto.getBankCode() +", num Of Record:" +bankATMTransactionDto.getAtmTransactionRecordDTOList().size() + ", total amount:"  + totalAmountOfBank );
		});
		
		 List<AtmTransactionRecordDTO> allATMRecords = bankAtmTransactionList.stream().map(bankATMTransactionDto -> {
		
			return bankATMTransactionDto.getAtmTransactionRecordDTOList();
		}).flatMap( x -> x.stream()).collect(Collectors.toList());
		
		 
		 Map<Integer, List<AtmTransactionRecordDTO>> issuerTransactionMap = allATMRecords.stream()
				  .collect(Collectors.groupingBy(AtmTransactionRecordDTO::getBOI_issuer_id));
		 
		 List<BankTerminatorDTO> issuersList= new ArrayList<>(); 
		  issuerTransactionMap.entrySet().forEach(bankATMTransactionDtoEntry -> {
			 
				Long totalAmountOfIssuers =bankATMTransactionDtoEntry.getValue().stream().mapToLong(AtmTransactionRecordDTO::getAmount).sum();
  			    BankTerminatorDTO bankIssuerDTO = new BankTerminatorDTO(); 
				bankIssuerDTO.setTotalSummaryAmount(totalAmountOfIssuers);
  			    bankIssuerDTO.setAtmTransactionRecordList(bankATMTransactionDtoEntry.getValue());
  			    bankIssuerDTO.setBank_code(String.valueOf( bankATMTransactionDtoEntry.getKey()));
  			    //bankIssuerDTO.set
				
  				///logger.info("For isssuer bank: {}" +", num Of Record: {}, total amount: {}" , bankIssuerDTO.getBank_code(), bankIssuerDTO.getAtmTransactionRecordList().size() , bankIssuerDTO.getTotalSummaryAmount() );
  				issuersList.add(bankIssuerDTO);
				
								
//				if (foundBankTerminatorDTO!=null) {
//					foundBankTerminatorDTO.setTotalSummaryAmount(totalAmountOfBank);
//					foundBankTerminatorDTO.setAtmTransactionRecordList(bankATMTransactionDto.getAtmTransactionRecordDTOList());
//				}
								
				//logger.info("For bank:"+bankATMTransactionDto.getBankCode() +", num Of Record:" +bankATMTransactionDto.getAtmTransactionRecordDTOList().size() + ", total amount:"  + totalAmountOfBank );
			});
		  issuersList.stream().forEach(bankIssuerDTO -> {
			  logger.info("For issuer bank: {}" +", num Of Record: {}, total amount: {}" , bankIssuerDTO.getBank_code(), bankIssuerDTO.getAtmTransactionRecordList().size() , bankIssuerDTO.getTotalSummaryAmount() );
		  });
		 
		//bankAtmTransactionList.stream().collect(Collectors.groupingBy(BankATMTransactionDTO::getBankCode));
		//int sum = bankAtmTransactionList.stream().filter(o -> o. > 10).mapToInt(Obj::getField).sum();
		
		executorService.shutdown();
    }
    
    private void invokeInParallelOld(List<BankTerminatorDTO> bankTerminatorDTOList) {
    	
    	StopWatch clock = new StopWatch();    	
    	try {
			clock.start();
			
			
			BaseBoRES baseSRQRES=new BaseBoRES();
			
			
		   List<TaskReq> sRQ166TaskList = bankTerminatorDTOList.stream().map(bankTerminatorDTO -> {
	            TaskReq req = new TaskReq();
	            modelMapper.map(bankTerminatorDTO, req);

	            return req;
	        }).collect(Collectors.toList());			

	        List<BaseTask> tasks = sRQ166TaskList.stream().map(req -> {
	            TaskImpl sRQ166Task1 = getTaskImpl();
	            sRQ166Task1.setBaseTaskReq(req);
	            return sRQ166Task1;
	        }).collect(Collectors.toList());

		   
//			List<BaseTask> tasks = bankTerminatorDTOList.stream().map(bankTerminatorDTO -> {
//				TaskImpl sRQ166Task1 = getTaskImpl();
//				sRQ166Task1.setbaseTaskReq(null);
//				//TaskReq req = new TaskReq();
//				//req.set //bankTerminatorDTO.getBank_code()
//				
//				//sRQ166Task1.setBaseTaskReq(req);
//				return sRQ166Task1;
//			}).collect(Collectors.toList());
			
			
			
			List<Future<BaseTaskRes>> tasksResults = getExecutorServiceAdapter().invokeAll(tasks);
	
			List<BaseTaskRes> parallelList = tasksResults.stream().map(future -> {
				try {
					return future.get();
				} catch (Exception e) {
					// createErrorResponse(baseBoREQ, baseSRQRES, ESRQOutputStatus.UNEXPECTED);
					e.printStackTrace();
					return null;
				}
	
			}).collect(Collectors.toList());
			if (parallelList != null) {
				baseSRQRES = handleCompletedTasks(parallelList,  baseSRQRES);
				
				baseSRQRES.setStatus("success");
				//createSuccessResponse(baseBoREQ, baseSRQRES);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
    }
    
    
    
    public BaseBoRES handleCompletedTasks(List<BaseTaskRes> parallelList, BaseBoRES baseBoRES){

        SRQOutput srq166Output = (SRQOutput) baseBoRES; // the initialize Bean

//        if (CollectionUtils.isEmpty(parallelList)) {
//            throw new CalBusinessException(SRQOutputStatusEnum.NO_RECORDS_FOUND);
//        }

        enhanceRecords(parallelList, srq166Output);

        return srq166Output;

    }
    
    private void enhanceRecords(List<BaseTaskRes> baseTaskResList, SRQOutput srq166Output) {

        // flat the list of all records
        List<BaseBoRES> phoalimTotalDbcrAmtByCardAndDateRecDTOList = baseTaskResList.stream().map(record -> (TaskRes) record)
                //.map(srq166Rec -> srq166Rec.getPhoalimTotalDbcrAmtByCardAndDateRecDTOList()).flatMap(Collection::stream)
                //.peek(rec -> {
                //    logger.debug(rec.toString());
                .collect(Collectors.toList());

//        SRQ166Input srq166Input = (SRQ166Input) baseBoREQ;

//        if (CollectionUtils.isEmpty(phoalimTotalDbcrAmtByCardAndDateRecDTOList)) {
//            throw new CalBusinessException(SRQOutputStatusEnum.NO_RECORDS_FOUND);
//        }
        
//        List<String> ids= phoalimTotalDbcrAmtByCardAndDateRecDTOList.stream().map(element -> 
//        	(TaskRes) element).map( t -> {
//        		return t.id;
//        	}).collect(Collectors.toList());
//        
//        srq166Output.setBank_ids(ids);

    }
    
    
    
	public void handleATMTransactions(ETLDTO etlDTO) {

		
		List<BankTerminatorDTO> bankTerminatorDTOList= bankTerminatorService.getAllSortedByIndex();
		bankTerminatorDTOList.stream().forEach(System.out::println);

		fillBankTerminatorRecords(bankTerminatorDTOList);
		//create record in db
		bankTerminatorDTOList.stream().forEach( dto->{
			
			System.out.println(dto);
		});
		
		//invoke in parallel the terminators
		
		//working in parallel
		String lineType = env.getProperty("lineType");
 		Date currentDate = new Date();
		String currentDateStr = StringUtils.getDateAsStringWithPattern(currentDate,"yyMMdd");
		currentDateStr="210826";
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

	public ExecutorServiceAdapter getExecutorServiceAdapter() {
		return executorServiceAdapter;
	}

	public void setExecutorServiceAdapter(ExecutorServiceAdapter executorServiceAdapter) {
		this.executorServiceAdapter = executorServiceAdapter;
	}


}
