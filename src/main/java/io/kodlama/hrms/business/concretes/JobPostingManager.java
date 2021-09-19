package io.kodlama.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.CompanyStaffService;
import io.kodlama.hrms.business.abstracts.JobPostingConfirmationService;
import io.kodlama.hrms.business.abstracts.JobPostingConfirmationTypeService;
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
import io.kodlama.hrms.entities.concretes.JobPostingConfirmationType;

@Service
public class JobPostingManager implements JobPostingService {

	private JobPostingDao jobPostingDao;
	private JobPostingConfirmationService jobPostingConfirmationService;
	private JobPostingConfirmationTypeService jobPostingConfirmationTypeService;
	private CompanyStaffService companyStaffService;

	@Autowired
	public JobPostingManager(JobPostingDao jobPostingDao, JobPostingConfirmationService jobPostingConfirmationService, JobPostingConfirmationTypeService jobPostingConfirmationTypeService, CompanyStaffService companyStaffService) {
		this.jobPostingDao = jobPostingDao;
		this.jobPostingConfirmationService = jobPostingConfirmationService;
		this.jobPostingConfirmationTypeService = jobPostingConfirmationTypeService;
		this.companyStaffService = companyStaffService;
	}

	@Override
	public Result add(JobPosting jobPosting) {

		jobPosting.setPostingDate(LocalDateTime.now());
		jobPosting.setActive(false);

		jobPostingDao.save(jobPosting);
		return new SuccessResult("İş ilanı onay aşamasındadır.");
	}

	@Override
	public Result update(JobPosting jobPosting) {

		jobPostingDao.save(jobPosting);
		return new SuccessResult("İş ilanı güncellendi.");
	}

	@Override
	public Result delete(int id) {

		jobPostingDao.deleteById(id);
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
	public Result confirm(int jobPostingId, int companyStaffId, int jobPostingConfirmationTypeId, boolean isConfirmed) {

		JobPosting jobPosting = getById(jobPostingId).getData();
		CompanyStaff companyStaff = companyStaffService.getById(companyStaffId).getData();
		JobPostingConfirmationType jobPostingConfirmationType = jobPostingConfirmationTypeService.getById(jobPostingConfirmationTypeId).getData();

		if (!isConfirmed) {
			delete(jobPosting.getId());
			return new ErrorResult("İş ilanı onaylanmadı.");
		}

		jobPosting.setActive(true);

		jobPostingDao.save(jobPosting);
		jobPostingConfirmationService.add(new JobPostingConfirmation(jobPosting, companyStaff, jobPostingConfirmationType, isConfirmed));
		return new SuccessResult("İş ilanı onaylandı.");
	}

	@Override
	public Result makeActiveOrPassive(int id, boolean isActive) {

		String statusMessage = isActive 
				? "İlan aktifleştirildi."
				: "İlan pasifleştirildi.";

		JobPosting jobPosting = getById(id).getData();
		jobPosting.setActive(isActive);

		update(jobPosting);
		return new SuccessResult(statusMessage);
	}
	
	@Override
	public DataResult<List<JobPosting>> getAllByIsActive(boolean isActive) {		
		return new SuccessDataResult<List<JobPosting>>(jobPostingDao.getByIsActive(isActive));
	}

	@Override
	public DataResult<List<JobPosting>> getAllActiveOnesByPage(int pageNo, int pageSize) {

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		return new SuccessDataResult<List<JobPosting>>(jobPostingDao.getByIsActive(true, pageable));
	}
	
	@Override
	public DataResult<List<JobPosting>> getAllActiveOnesSortedByPostingDate() {
		
		Sort sort = Sort.by(Sort.Direction.DESC, "postingDate");
		
		return new SuccessDataResult<List<JobPosting>>(jobPostingDao.getByIsActive(true, sort));
	}

	@Override
	public DataResult<List<JobPosting>> getAllActiveOnesByPageSortedByPostingDate(int pageNo, int pageSize) {

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("postingDate").descending());

		return new SuccessDataResult<List<JobPosting>>(jobPostingDao.getByIsActive(true, pageable));
	}

	@Override
	public DataResult<List<JobPosting>> getAllActiveOnesSortedByPostingDateTop6() {

		List<JobPosting> result = getAllActiveOnesByPageSortedByPostingDate(1, 6).getData();

		return new SuccessDataResult<List<JobPosting>>(result);
	}

	@Override
	public DataResult<List<JobPosting>> getAllActiveOnesByEmployerIdSortedByPostingDate(int employerId) {
		
		Sort sort = Sort.by(Sort.Direction.DESC, "postingDate");
		
		return new SuccessDataResult<List<JobPosting>>(jobPostingDao.getByIsActiveAndEmployer_Id(true, employerId, sort));
	}
	
	@Override
	public DataResult<List<JobPosting>> getAllActiveOnesFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(int cityId, int jobTitleId, int workingTimeId, int workingTypeId) {
		
		List<JobPosting> result = new ArrayList<JobPosting>();
		
		Stream<JobPosting> stream = getAllActiveOnesSortedByPostingDate().getData().stream();		
		
		Predicate<JobPosting> cityCondition = cityId != 0 
				? (jobPosting -> jobPosting.getCity().getId() == cityId) 
				: (jobPosting -> jobPosting.getCity().getId() > 0);
		Predicate<JobPosting> jobTitleCondition = jobTitleId != 0 
				? (jobPosting -> jobPosting.getJobTitle().getId() == jobTitleId)
				: (jobPosting -> jobPosting.getJobTitle().getId() > 0);
		Predicate<JobPosting> workingTimeCondition = workingTimeId != 0 
				? (jobPosting -> jobPosting.getWorkingTime().getId() == workingTimeId)
				: (jobPosting -> jobPosting.getWorkingTime().getId() > 0);
		Predicate<JobPosting> workingTypeCondition = workingTypeId != 0 
				? (jobPosting -> jobPosting.getWorkingType().getId() == workingTypeId)
				: (jobPosting -> jobPosting.getWorkingType().getId() > 0);	 
		
		stream.filter(workingTimeCondition).filter(workingTypeCondition).filter(cityCondition).filter(jobTitleCondition).forEach(jobPosting -> result.add(jobPosting));
		
		return new SuccessDataResult<List<JobPosting>>(result);
	}

	@Override
	public DataResult<List<JobPosting>> getAllActiveOnesByPageFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(int cityId, int jobTitleId, int workingTimeId, int workingTypeId, int pageNo, int pageSize) {
		
		int skipCount = (pageNo -1) * pageSize;	     
		
		List<JobPosting> result = getAllActiveOnesFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(cityId, jobTitleId, workingTimeId, workingTypeId).getData();	

		return new SuccessDataResult<List<JobPosting>>(result.stream().skip(skipCount).limit(pageSize).collect(Collectors.toList()));
	}

	@Override
	public DataResult<List<JobPosting>> getAllOnesThatWaitingForPostingConfirmation() {
		
		List<JobPosting> result = new ArrayList<JobPosting>();
		List<JobPosting> passiveJobPosting = getAllByIsActive(false).getData();
		
		for (JobPosting jobPosting : passiveJobPosting) {
			if (jobPostingConfirmationService.getByJobPostingId(jobPosting.getId()).getData() == null) {
				result.add(jobPosting);
			}
		}
		
		return new SuccessDataResult<List<JobPosting>>(result);
	}

}
