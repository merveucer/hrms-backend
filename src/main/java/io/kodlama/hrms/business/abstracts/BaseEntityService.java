package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;

public interface BaseEntityService<T> {

	Result add(T entity);

	Result update(T entity);

	Result delete(int id);

	DataResult<List<T>> getAll();

	DataResult<T> getById(int id);

}
