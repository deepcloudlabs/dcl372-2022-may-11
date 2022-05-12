package com.example.world.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.world.dao.CountryDao;
import com.example.world.entity.Country;

@RestController
@RequestScope
public class WorldRestController {

	private final CountryDao countryDao;
	
	public WorldRestController(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	@GetMapping("continents")
	public Set<String> getContinents(){
		return countryDao.getAllContinents();
	}
	
	@GetMapping("countries")
	public List<Country> getCountriesByContinent(@RequestParam String continent){
		return countryDao.findCountriesByContinent(continent);
	}
	
}
