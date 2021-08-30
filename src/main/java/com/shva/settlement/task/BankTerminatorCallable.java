package com.shva.settlement.task;

import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shva.settlement.dto.AtmTransactionRecordDTO;
import com.shva.settlement.dto.BankATMTransactionDTO;
import com.shva.settlement.dto.BankTerminatorDTO;
import com.shva.settlement.service.AtmTransactionService;
import com.shva.settlement.util.StringUtils;

//@Component
public class BankTerminatorCallable implements Callable<BankATMTransactionDTO>{

	private BankTerminatorDTO bankTerminatorDTO;

//	@Autowired
	AtmTransactionService atmTransactionService;
	
    public BankTerminatorCallable(BankTerminatorDTO bankTerminatorDTO,AtmTransactionService atmTransactionService) {
        this.bankTerminatorDTO = bankTerminatorDTO;
        this.atmTransactionService = atmTransactionService;
     }
    public BankATMTransactionDTO call() throws Exception {
    	
 		Date currentDate = new Date();
		String currentDateStr = StringUtils.getDateAsStringWithPattern(currentDate,"yyMMdd");
		int currentDateInt = Integer.parseInt(currentDateStr);
		//temp
		currentDateInt=210826;
		
		List<AtmTransactionRecordDTO> atmTransactionRecordDTOs = this.atmTransactionService.getAtmTransactionByCreatedDateAndBankCode(currentDateInt,bankTerminatorDTO.getBank_code());
		BankATMTransactionDTO bankATMTransactionDTO = new BankATMTransactionDTO(atmTransactionRecordDTOs,currentDateInt, bankTerminatorDTO.getBank_code()); 
		return 	bankATMTransactionDTO;
//    	atmTransactionService.getAtmTransactionByCreatedDate(null)
//        return bankTerminatorDTO.getBank_code();
    }
}
