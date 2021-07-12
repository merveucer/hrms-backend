package io.kodlama.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.CompanyStaffService;
import io.kodlama.hrms.business.abstracts.JobPostingConfirmationService;
import io.kodlama.hrms.business.abstracts.JobPostingService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.ErrorResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.JobPostingDao;
import io.kodlama.hrms.entities.concretes.CompanyStaff;
import io.kodlama.hrms.entities.concretes.JobPosting;
import io.kodlama.hrms.entities.concretes.JobPostingConfirmation;
import io.kodlama.hrms.entities.dtos.JobPostingWithEmployerAndJobTitleDto;

@Service
public class JobPostingManager implements JobPostingService {

	private JobPostingDao jobPostingDao;
	private JobPostingConfirmationService jobPostingConfirmationService;
	private CompanyStaffService companyStaffService;

	public JobPostingManager(JobPostingDao jobPostingDao, JobPostingConfirmationService jobPostingConfirmationService, CompanyStaffService companyStaffService) {
		this.jobPostingDao = jobPostingDao;
		this.jobPostingConfirmationService = jobPostingConfirmationService;
		this.companyStaffService = companyStaffService;
	}

	@Override
	public Result add(JobPosting jobPosting) {

		jobPosting.setPostingDate(LocalDateTime.now());
		jobPosting.setActive(false);
		jobPosting.setConfirmed(false);

		jobPostingDao.save(jobPosting);
		return new SuccessResult("İş ilanı onay aşamasındadır.");
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
	public Result confirm(int jobPostingId, int companyStaffId, boolean isConfirmed) {
		
		JobPosting jobPosting = getById(jobPostingId).getData();
		CompanyStaff companyStaff = companyStaffService.getById(companyStaffId).getData();
		
		if(!isConfirmed) {
			delete(jobPosting);
			return new ErrorResult("İş ilanı onaylanmadı.");
		}
		
		jobPosting.setConfirmed(isConfirmed);
		
		update(jobPosting);
		jobPostingConfirmationService.add(new JobPostingConfirmation(jobPosting, companyStaff));
		return new SuccessResult("İş ilanı onaylandı.");
	}

	@Override
	public Result doActiveOrPassive(int id, boolean isActive) {

		String statusMessage = isActive ? "İlan aktifleştirildi." : "İlan pasifleştirildi.";
		
		JobPosting jobPosting = getById(id).getData();
		jobPosting.setActive(isActive);

		update(jobPosting);
		return new SuccessResult(statusMessage);
	}

	@Override
	public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetails() {
		return new SuccessDataResult<List<JobPostingWithEmployerAndJobTitleDto>>(jobPostingDao.getJobPostingWithEmployerAndJobTitleDtoByIsActive(true));
	}

	@Override
	public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetailsSortedByPostingDate() {

		Sort sort = Sort.by(Sort.Direction.DESC, "postingDate");

		return new SuccessDataResult<List<JobPostingWithEmployerAndJobTitleDto>>(jobPostingDao.getJobPostingWithEmployerAndJobTitleDtoByIsActive(true, sort));
	}
	
	@Override
	public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetailsSortedByPostingDateTop6() {
		
		List<JobPostingWithEmployerAndJobTitleDto> result = getAllActiveJobPostingDetailsSortedByPostingDate().getData();
		
		List<JobPostingWithEmployerAndJobTitleDto> listTop6 = new ArrayList<JobPostingWithEmployerAndJobTitleDto>();
		listTop6.add(result.get(0));
		listTop6.add(result.get(1));
		listTop6.add(result.get(2));
		listTop6.add(result.get(3));
		listTop6.add(result.get(4));
		listTop6.add(result.get(5));		
		
		return new SuccessDataResult<List<JobPostingWithEmployerAndJobTitleDto>>(listTop6);
	}

	@Override
	public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetailsByCompanyName(String companyName) {
		return new SuccessDataResult<List<JobPostingWithEmployerAndJobTitleDto>>(jobPostingDao.getJobPostingWithEmployerAndJobTitleDtoByIsActiveAndCompanyName(true, companyName));
	}

}
