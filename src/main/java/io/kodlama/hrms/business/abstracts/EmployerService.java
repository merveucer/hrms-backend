package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.Employer;

public interface EmployerService extends BaseEntityService<Employer> {
	
	DataResult<List<Employer>> getByIsActivatedAndIsConfirmed(boolean isActivated, boolean isConfirmed);
	
	Result activate(String code);
	
	Result confirm(Integer employerId, Integer companyStaffId, boolean isConfirmed);

}
