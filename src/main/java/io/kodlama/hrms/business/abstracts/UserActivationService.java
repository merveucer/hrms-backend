package io.kodlama.hrms.business.abstracts;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.entities.concretes.UserActivation;

public interface UserActivationService extends BaseEntityService<UserActivation> {

	DataResult<UserActivation> getByCode(String code);

	DataResult<UserActivation> getByUserId(int userId);

}
