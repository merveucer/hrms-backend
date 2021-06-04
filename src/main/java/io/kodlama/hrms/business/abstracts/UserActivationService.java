package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.User;
import io.kodlama.hrms.entities.concretes.UserActivation;

public interface UserActivationService {
	
	Result add(UserActivation userActivation);
	
	Result update(UserActivation userActivation);
	
	Result delete(UserActivation userActivation);
	
	DataResult<List<UserActivation>> getAll();
	
	DataResult<UserActivation> getById(int id);
	
	DataResult<UserActivation> getByCode(String code);
	
	DataResult<UserActivation> getByUser(User user);

}
