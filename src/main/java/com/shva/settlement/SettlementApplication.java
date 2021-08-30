package com.shva.settlement;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.support.ExecutorServiceAdapter;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.shva.settlement.dto.ETLDTO;
import com.shva.settlement.service.ETLService;
import com.shva.settlement.task.ScheduledTasks;

//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableAsync
@EnableScheduling
//@Configuration
//@EnableSwagger2WebMvc
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

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			logger.info("first time -> runCounter immediately");
			logger.info("do something...");
			logger.info("finish runCounter immediately");


		};
	}

	@Bean(value = "myLogger")
	@Scope("prototype")
	Logger logger(InjectionPoint injectionPoint) {
		String callingClass = "General";
		if (injectionPoint != null) {
			if (injectionPoint.getMember() != null) {
				callingClass = injectionPoint.getMember().getDeclaringClass().getName();
			} else if (injectionPoint.getField() != null) {
				callingClass = injectionPoint.getField().getDeclaringClass().getName();
			} else if (injectionPoint.getMethodParameter() != null) {
				callingClass = injectionPoint.getMethodParameter().getContainingClass().getName();
			}
		}

		Logger myLogger = LoggerFactory.getLogger(callingClass);

		return myLogger;
	}

	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);
		mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

		return mapper;

	}
	
//    @Bean
//    public Docket api() { 
//        return new Docket(DocumentationType.SWAGGER_2)  
//          .select()                                  
//          .apis(RequestHandlerSelectors.any())              
//          .paths(PathSelectors.any())                          
//          .build();                                           
//    }	

// no need	(25/08/21)
//	@Bean(destroyMethod = "shutdown")
//	public Executor taskExecutor() {
//		return Executors.newScheduledThreadPool(100);
//	}
	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	    registry.addResourceHandler("swagger-ui.html")
//	      .addResourceLocations("classpath:/META-INF/resources/");
//
//	    registry.addResourceHandler("/webjars/**")
//	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
//	}	

	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

		List<ETLDTO> etoList = etlService.getAllETL();
		
		etoList.stream().forEach(etoDTO -> {
		
			Runnable runnable = () -> {
				System.out.println("start run....for :"+etoDTO.getName());
 				scheduledTasks.handleATMTransactions(etoDTO);
//				scheduledTasks.runETLTransaction(etoDTO);
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
	
	//from 25/08/2021
//	@Bean
//	public ThreadPoolTaskExecutor taskExecutor1() {
//		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//		executor.setWaitForTasksToCompleteOnShutdown(true);
//		executor.setCorePoolSize(10);
//		executor.setMaxPoolSize(10);
//		return executor;
//	}
//	
//	@Bean
//	ExecutorServiceAdapter executorServiceAdapter() {
//		ExecutorServiceAdapter esa=new ExecutorServiceAdapter(null);
//		return esa;
//	}
//	

}
