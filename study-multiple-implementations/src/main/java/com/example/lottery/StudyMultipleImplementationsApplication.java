package com.example.lottery;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.lottery.service.RandomNumberService;
import com.example.lottery.service.business.FastRandomNumberService;
import com.example.lottery.service.business.SecureRandomNumberService;

@SpringBootApplication
public class StudyMultipleImplementationsApplication  implements ApplicationRunner{
	private final ApplicationContext container;
	
	public StudyMultipleImplementationsApplication(ApplicationContext contianer) {
		this.container = contianer;
	}

	public static void main(String[] args) {
		SpringApplication.run(StudyMultipleImplementationsApplication.class, args);
	}

	@SuppressWarnings("unused")
	@Override
	public void run(ApplicationArguments args) throws Exception {
		var randomNumberServices = container.getBeansOfType(RandomNumberService.class);
		randomNumberServices.forEach((name,service) -> System.err.println("%s: %s".formatted(name,service.getClass())));
		var fast = container.getBean("fastRandomNumberService", RandomNumberService.class);
		System.err.println(fast.getClass());
		var secure = container.getBean("secureRandomNumberService", RandomNumberService.class);
		System.err.println(secure.getClass());
		var fastService = container.getBean(FastRandomNumberService.class);
		var secureService = container.getBean(SecureRandomNumberService.class);
		var components = container.getBeansOfType(Object.class);
		components.forEach((name,service) -> System.err.println("%18s: %s".formatted(name,service.getClass())));
		
	}

}
