package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.JobPosting;

public interface JobPostingService extends BaseEntityService<JobPosting> {

	Result confirm(int employerId, int companyStaffId, boolean isConfirmed);

	Result doActiveOrPassive(int id, boolean isActive);

	DataResult<List<JobPosting>> getAllActiveOnes(int pageNo, int pageSize);

	DataResult<List<JobPosting>> getAllActiveOnesSortedByPostingDate(int pageNo, int pageSize);

	DataResult<List<JobPosting>> getAllActiveOnesSortedByPostingDateTop6();

	DataResult<List<JobPosting>> getAllActiveOnesByEmployerId(int employerId);

	DataResult<List<JobPosting>> getAllActiveOnesFilteredByWorkingTimeAndWorkingTypeAndCity(int workingTimeId, int workingTypeId, int cityId, int pageNo, int pageSize);

}
