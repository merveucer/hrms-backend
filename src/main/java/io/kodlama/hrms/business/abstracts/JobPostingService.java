package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.JobPosting;
import io.kodlama.hrms.entities.dtos.JobPostingWithEmployerAndJobTitleDto;

public interface JobPostingService {
	
	Result add(JobPosting jobPosting);
	
	Result update(JobPosting jobPosting);
	
	Result delete(JobPosting jobPosting);

	DataResult<List<JobPosting>> getAll();
	
	DataResult<JobPosting> getById(int id);
	
	DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getActiveJobPostingDetails();
	
	DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getActiveJobPostingDetailsSortedByPostingDate();
	
	DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getActiveJobPostingDetailsByEmployerId(int employerId);

}
