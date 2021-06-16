package io.kodlama.hrms.business.abstracts;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.entities.concretes.JobTitle;

public interface JobTitleService extends BaseEntityService<JobTitle> {

	DataResult<JobTitle> getByTitle(String title);

}
