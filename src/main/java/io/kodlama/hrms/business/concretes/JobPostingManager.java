package io.kodlama.hrms.business.concretes;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.JobPostingService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.JobPostingDao;
import io.kodlama.hrms.entities.concretes.JobPosting;
import io.kodlama.hrms.entities.dtos.JobPostingWithEmployerAndJobTitleDto;

@Service
public class JobPostingManager implements JobPostingService {
	
	private JobPostingDao jobPostingDao;
	
	public JobPostingManager(JobPostingDao jobPostingDao) {
		this.jobPostingDao = jobPostingDao;
	}

	@Override
	public Result add(JobPosting jobPosting) {
		
		jobPostingDao.save(jobPosting);
		return new SuccessResult("İş ilanı eklendi.");
	}

	@Override
	public Result update(JobPosting jobPosting) {
		
		jobPostingDao.save(jobPosting);
		return new SuccessResult("İş ilanı güncellendi.");
	}

	@Override
	public Result delete(JobPosting jobPosting) {
		
		jobPostingDao.delete(jobPosting);
		return new SuccessResult("İş ilanı silindi.");
	}

	@Override
	public DataResult<List<JobPosting>> getAll() {
		return new SuccessDataResult<List<JobPosting>>(jobPostingDao.findAll());
	}

	@Override
	public DataResult<JobPosting> getById(int id) {
		return new SuccessDataResult<JobPosting>(jobPostingDao.getById(id));
	}

	@Override
	public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getActiveJobPostingDetails() {		
		return new SuccessDataResult<List<JobPostingWithEmployerAndJobTitleDto>>(jobPostingDao.getJobPostingWithEmployerAndJobTitleDetailsByIsActive(true));
	}

	@Override
	public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getActiveJobPostingDetailsSortedByPostingDate() {
		
		Sort sort = Sort.by(Sort.Direction.DESC, "postingDate");
		
		return new SuccessDataResult<List<JobPostingWithEmployerAndJobTitleDto>>(jobPostingDao.getJobPostingWithEmployerAndJobTitleDetailsByIsActive(true, sort));
	}

	@Override
	public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getActiveJobPostingDetailsByEmployerId(int employerId) {		
		return new SuccessDataResult<List<JobPostingWithEmployerAndJobTitleDto>>(jobPostingDao.getJobPostingWithEmployerAndJobTitleDetailsByIsActiveAndEmployer_EmployerId(true, employerId));
	}

}
