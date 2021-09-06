package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.entities.concretes.UserActivation;

public interface UserActivationService extends BaseEntityService<UserActivation> {

	DataResult<UserActivation> getByCode(String code);

	DataResult<UserActivation> getByUserId(int userId);
	
	DataResult<List<UserActivation>> getAllByIsActivated(boolean isActivated);

}
