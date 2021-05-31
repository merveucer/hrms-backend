package io.kodlama.hrms.business.abstracts;

import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.UserConfirmation;

public interface UserConfirmationService {
	
	Result add(UserConfirmation userConfirmation);

}
