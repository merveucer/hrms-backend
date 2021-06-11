package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;

public interface BaseEntityService<T> {
	
	Result add(T t);
	
	Result update(T t);
	
	Result delete(T t);

	DataResult<List<T>> getAll();
	
	DataResult<T> getById(int id);

}
