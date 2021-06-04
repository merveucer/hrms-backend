package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.City;

public interface CityService {
	
	Result add(City city);
	
	Result update(City city);
	
	Result delete(City city);

	DataResult<List<City>> getAll();
	
	DataResult<City> getById(int id);

}
