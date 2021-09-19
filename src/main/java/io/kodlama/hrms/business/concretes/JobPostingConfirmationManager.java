package io.kodlama.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.EmailService;
import io.kodlama.hrms.business.abstracts.JobPostingConfirmationService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.JobPostingConfirmationDao;
import io.kodlama.hrms.entities.concretes.JobPostingConfirmation;

@Service
public class JobPostingConfirmationManager implements JobPostingConfirmationService {
	
	private JobPostingConfirmationDao jobPostingConfirmationDao;
	private EmailService emailService;

	@Autowired
	public JobPostingConfirmationManager(JobPostingConfirmationDao jobPostingConfirmationDao, EmailService emailService) {
		this.jobPostingConfirmationDao = jobPostingConfirmationDao;
		this.emailService = emailService;
	}

	@Override
	public Result add(JobPostingConfirmation jobPostingConfirmation) {
		
		jobPostingConfirmation.setIsConfirmedDate(LocalDateTime.now());
		
		jobPostingConfirmationDao.save(jobPostingConfirmation);
		emailService.sendEmail(jobPostingConfirmation.getJobPosting().getEmployer());
		return new SuccessResult();
	}

	@Override
	public Result update(JobPostingConfirmation jobPostingConfirmation) {
		
		jobPostingConfirmationDao.save(jobPostingConfirmation);
		return new SuccessResult();
	}

	@Override
	public Result delete(int id) {
		
		jobPostingConfirmationDao.deleteById(id);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<JobPostingConfirmation>> getAll() {
		return new SuccessDataResult<List<JobPostingConfirmation>>(jobPostingConfirmationDao.findAll());
	}

	@Override
	public DataResult<JobPostingConfirmation> getById(int id) {
		return new SuccessDataResult<JobPostingConfirmation>(jobPostingConfirmationDao.getById(id));
	}

	@Override
	public DataResult<JobPostingConfirmation> getByJobPostingId(int jobPostingId) {		
		return new SuccessDataResult<JobPostingConfirmation>(jobPostingConfirmationDao.getByJobPosting_Id(jobPostingId));
	}

}
