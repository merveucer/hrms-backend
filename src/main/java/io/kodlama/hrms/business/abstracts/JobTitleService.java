package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.JobTitle;

public interface JobTitleService {
	
	Result add(JobTitle jobTitle);
	
	Result update(JobTitle jobTitle);
	
	Result delete(JobTitle jobTitle);

	DataResult<List<JobTitle>> getAll();
	
	DataResult<JobTitle> getById(int id);
	
	DataResult<JobTitle> getByTitle(String title);

}
