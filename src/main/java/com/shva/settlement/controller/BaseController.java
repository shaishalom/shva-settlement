package com.shva.settlement.controller;

import com.shva.settlement.dto.StatusDTO;
import com.shva.settlement.exception.ProjBusinessException;

//@CrossOrigin(origins = "*")
public abstract class BaseController {

	public StatusDTO handleBusinessException(ProjBusinessException projBusinessException) {
		StatusDTO status = projBusinessException.getStatus();
		return status;
	}

	
	
}

