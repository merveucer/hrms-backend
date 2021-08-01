package io.kodlama.hrms.business.abstracts;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.entities.concretes.UpdatedEmployer;

public interface UpdatedEmployerService extends BaseEntityService<UpdatedEmployer> {

	DataResult<UpdatedEmployer> getByEmployerId(int employerId);

}
