package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.Employer;

public interface EmployerService extends BaseEntityService<Employer> {

	Result activate(String code);

	Result confirm(Integer employerId, Integer companyStaffId, boolean isConfirmed);

	DataResult<List<Employer>> getAllByIsActivatedAndIsConfirmed(boolean isActivated, boolean isConfirmed);

}
