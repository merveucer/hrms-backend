package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.UserConfirmation;

public interface UserConfirmationService {
	
	Result add(UserConfirmation userConfirmation);
	
	Result update(UserConfirmation userConfirmation);
	
	Result delete(UserConfirmation userConfirmation);
	
	DataResult<List<UserConfirmation>> getAll();
	
	DataResult<UserConfirmation> getById(int id);

}
