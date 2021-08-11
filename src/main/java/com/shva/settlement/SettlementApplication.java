package com.shva.settlement;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import com.shva.settlement.dto.ETLDTO;
import com.shva.settlement.service.ETLService;
import com.shva.settlement.task.ScheduledTasks;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class SettlementApplication implements SchedulingConfigurer {

	
	@Autowired
	public Logger logger;

	@Autowired
	public ScheduledTasks scheduledTasks;
	
	@Autowired
	ETLService etlService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SettlementApplication.class, args);
	}
	
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

		List<ETLDTO> etoList = etlService.getAllETL();
		
		etoList.stream().forEach(etoDTO -> {
		
			Runnable runnable = () -> {
				System.out.println("start run....for :"+etoDTO.getName());
				scheduledTasks.runETLTransaction(etoDTO);
				System.out.println("end run....for "+etoDTO.getName());
			};
	
			Trigger trigger = new Trigger() {
	
				@Override
	
				public Date nextExecutionTime(TriggerContext triggerContext) {
	
					CronTrigger crontrigger = new CronTrigger(etoDTO.getSchedule_crone());
	
					return crontrigger.nextExecutionTime(triggerContext);
	
				}
	
			};
	
			taskRegistrar.addTriggerTask(runnable, trigger);
		});

	}
	

}
