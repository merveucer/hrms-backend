package io.kodlama.hrms.business.abstracts;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.entities.concretes.JobPostingConfirmation;

public interface JobPostingConfirmationService extends BaseEntityService<JobPostingConfirmation>{
	
	DataResult<JobPostingConfirmation> getByJobPostingId(int jobPostingId);

}
