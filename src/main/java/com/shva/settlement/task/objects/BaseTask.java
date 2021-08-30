package com.shva.settlement.task.objects;

import java.util.concurrent.Callable;

public abstract class BaseTask implements Callable<BaseTaskRes> {

	BaseTaskReq baseTaskReq;
	BaseTaskRes baseTaskRes;		

	@Override
	public BaseTaskRes call() {

		baseTaskRes = (BaseTaskRes) executeTaskFlow(baseTaskReq);
		

		return baseTaskRes;
	}

	/*
	 * abstract method to execute business in each BO
	 */
	public abstract BaseBoRES executeTaskFlow(BaseBoREQ baseBoREQ);

	
	public BaseTaskReq getBaseTaskReq() {
		return baseTaskReq;
	}

	public void setBaseTaskReq(BaseTaskReq baseTaskReq) {
		this.baseTaskReq = baseTaskReq;
	}

	public BaseTaskRes getBaseTaskRes() {
		return baseTaskRes;
	}

	public void setBaseTaskRes(BaseTaskRes baseTaskRes) {
		this.baseTaskRes = baseTaskRes;
	}
	

}
