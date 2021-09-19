package io.kodlama.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.CityService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.CityDao;
import io.kodlama.hrms.entities.concretes.City;

@Service
public class CityManager implements CityService {

	private CityDao cityDao;

	@Autowired
	public CityManager(CityDao cityDao) {
		this.cityDao = cityDao;
	}

	@Override
	public Result add(City city) {

		cityDao.save(city);
		return new SuccessResult("Şehir eklendi.");
	}

	@Override
	public Result update(City city) {

		cityDao.save(city);
		return new SuccessResult("Şehir güncellendi.");
	}

	@Override
	public Result delete(int id) {

		cityDao.deleteById(id);
		return new SuccessResult("Şehir silindi.");
	}

	@Override
	public DataResult<List<City>> getAll() {
		
		Sort sort = Sort.by(Sort.Direction.ASC, "city");
		
		return new SuccessDataResult<List<City>>(cityDao.findAll(sort));
	}

	@Override
	public DataResult<City> getById(int id) {
		return new SuccessDataResult<City>(cityDao.getById(id));
	}

}
