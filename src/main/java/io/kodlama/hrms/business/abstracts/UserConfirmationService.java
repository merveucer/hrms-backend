package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.entities.concretes.UserConfirmation;

public interface UserConfirmationService extends BaseEntityService<UserConfirmation> {

	DataResult<List<UserConfirmation>> getAllByUserId(int userId);
	
	DataResult<List<UserConfirmation>> getAllByIsConfirmedAndUserConfirmationTypeId(boolean isConfirmed, int userConfirmationTypeId);

}
