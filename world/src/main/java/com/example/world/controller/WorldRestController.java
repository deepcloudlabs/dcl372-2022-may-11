package com.example.world.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.world.dao.CountryDao;
import com.example.world.entity.Country;

@RestController
@RequestMapping("/api/v1")
@RequestScope
public class WorldRestController {

	private final CountryDao countryDao;

	public WorldRestController(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	// /world/api/v1/continents
	@GetMapping("continents")
	public Set<String> getContinents() {
		return countryDao.getAllContinents();
	}

	// /world/api/v1/countries?continent=Asia
	@GetMapping(value = "countries", params = { "continent" })
	public List<Country> getCountriesByContinent(@RequestParam String continent) {
		return countryDao.findCountriesByContinent(continent);
	}

}
