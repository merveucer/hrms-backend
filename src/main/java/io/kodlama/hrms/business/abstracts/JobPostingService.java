package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.JobPosting;

public interface JobPostingService extends BaseEntityService<JobPosting> {

	Result confirm(int employerId, int companyStaffId, int jobPostingConfirmationTypeId, boolean isConfirmed);

	Result makeActiveOrPassive(int id, boolean isActive);
	
	DataResult<List<JobPosting>> getAllByIsActive(boolean isActive);

	DataResult<List<JobPosting>> getAllActiveOnesByPage(int pageNo, int pageSize);
	
	DataResult<List<JobPosting>> getAllActiveOnesSortedByPostingDate();

	DataResult<List<JobPosting>> getAllActiveOnesByPageSortedByPostingDate(int pageNo, int pageSize);

	DataResult<List<JobPosting>> getAllActiveOnesSortedByPostingDateTop6();

	DataResult<List<JobPosting>> getAllActiveOnesByEmployerIdSortedByPostingDate(int employerId);
	
	DataResult<List<JobPosting>> getAllActiveOnesFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(int cityId, int jobTitleId, int workingTimeId, int workingTypeId);

	DataResult<List<JobPosting>> getAllActiveOnesByPageFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(int cityId, int jobTitleId, int workingTimeId, int workingTypeId, int pageNo, int pageSize);
	
	DataResult<List<JobPosting>> getAllOnesThatWaitingForPostingConfirmation();

}
