package com.shva.settlement.task.objects;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskImpl extends BaseTask {

	@Autowired
	Logger						logger;

	@Override
	public BaseBoRES executeTaskFlow(BaseBoREQ baseBoREQ) {
		
		TaskRes srq166TaskRes = new TaskRes();
		TaskReq srq166TaskReq = (TaskReq) baseBoREQ;
		srq166TaskRes.setId(srq166TaskReq.getId());
		return srq166TaskRes;		
		
	}	
}
