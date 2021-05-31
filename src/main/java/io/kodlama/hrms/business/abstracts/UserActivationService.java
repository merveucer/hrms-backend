package io.kodlama.hrms.business.abstracts;

import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.UserActivation;

public interface UserActivationService {
	
	Result add(UserActivation userActivation);
	
	Result update(UserActivation userActivation);

}
