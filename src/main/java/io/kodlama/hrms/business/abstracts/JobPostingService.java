package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.JobPosting;
import io.kodlama.hrms.entities.dtos.JobPostingWithEmployerAndJobTitleDto;

public interface JobPostingService extends BaseEntityService<JobPosting> {
	
	Result confirm(int employerId, int companyStaffId, boolean isConfirmed);

	Result doActiveOrPassive(int id, boolean isActive);

	DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetails();

	DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetailsSortedByPostingDate();
	
	DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetailsSortedByPostingDateTop6();

	DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetailsByCompanyName(String companyName);

}
