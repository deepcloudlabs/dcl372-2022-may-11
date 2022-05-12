package com.example.world;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.world.dao.CityDao;
import com.example.world.dao.CountryDao;
import com.example.world.dao.WorldDao;
import com.example.world.dao.inmemory.InMemoryWorldDao;

@SpringBootApplication
public class WorldApplication implements ApplicationRunner {
	private final ApplicationContext applicationContext;
	
	public WorldApplication(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public static void main(String[] args) {
		SpringApplication.run(WorldApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		var cityDao = applicationContext.getBean(CityDao.class);
		cityDao.findCitiesByCountryCode("TUR")
		       .forEach(System.err::println);
		var countryDao = applicationContext.getBean(CountryDao.class);
		countryDao.findCountriesByContinent("Asia")
		          .forEach(System.err::println);
		var worldDao = applicationContext.getBean(WorldDao.class);
		var continents = worldDao.getAllContinents();
		System.err.println(continents);
		var inMemoryWorldDao = applicationContext.getBean(InMemoryWorldDao.class);
		System.err.println(cityDao.getClass());
		System.err.println(countryDao.getClass());
		System.err.println(worldDao.getClass());
		System.err.println(inMemoryWorldDao.getClass());
	}

}
