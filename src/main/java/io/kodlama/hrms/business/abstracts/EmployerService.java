package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.Employer;
import io.kodlama.hrms.entities.concretes.UserActivation;

public interface EmployerService {
	
	Result add(Employer employer);
	
	Result update(Employer employer);
	
	Result delete(Employer employer);
	
	DataResult<List<Employer>> getAll();
	
	DataResult<Employer> getById(int id);
	
	Result activate(UserActivation userActivation);

}
