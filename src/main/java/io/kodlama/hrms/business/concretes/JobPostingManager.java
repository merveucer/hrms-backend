package io.kodlama.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

		if (!isConfirmed) {
			delete(jobPosting);
			return new ErrorResult("İş ilanı onaylanmadı.");
		}

		jobPosting.setConfirmed(isConfirmed);

		jobPostingDao.save(jobPosting);
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
	public DataResult<List<JobPosting>> getAllActiveOnes() {		
		return new SuccessDataResult<List<JobPosting>>(jobPostingDao.getByIsActive(true));
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
	public DataResult<List<JobPosting>> getAllActiveOnesByEmployerId(int employerId) {
		return new SuccessDataResult<List<JobPosting>>(jobPostingDao.getByIsActiveAndEmployer_Id(true, employerId));
	}
	
	@Override
	public DataResult<List<JobPosting>> getAllActiveOnesFilteredByWorkingTimeAndWorkingTypeAndCity(int workingTimeId, int workingTypeId, int cityId) {
		
		List<JobPosting> result = getAllActiveOnesFilteredByWorkingTimeAndWorkingTypeAndCityBase(workingTimeId, workingTypeId, cityId);
		
		return new SuccessDataResult<List<JobPosting>>(result);
	}

	@Override
	public DataResult<List<JobPosting>> getAllActiveOnesByPageFilteredByWorkingTimeAndWorkingTypeAndCity(int workingTimeId, int workingTypeId, int cityId, int pageNo, int pageSize) {
		
		int skipCount = (pageNo -1) * pageSize;	     
		
		List<JobPosting> result = getAllActiveOnesFilteredByWorkingTimeAndWorkingTypeAndCityBase(workingTimeId, workingTypeId, cityId);	

		return new SuccessDataResult<List<JobPosting>>(result.stream().skip(skipCount).limit(pageSize).collect(Collectors.toList()));
	}
	
	private List<JobPosting> getAllActiveOnesFilteredByWorkingTimeAndWorkingTypeAndCityBase(int workingTimeId, int workingTypeId, int cityId) {
		
		Stream<JobPosting> stream = getAllActiveOnes().getData().stream();

		Predicate<JobPosting> workingTimeCondition = jobPosting -> jobPosting.getWorkingTime().getId() == workingTimeId;
		Predicate<JobPosting> workingTypeCondition = jobPosting -> jobPosting.getWorkingType().getId() == workingTypeId;
		Predicate<JobPosting> cityCondition = jobPosting -> jobPosting.getCity().getId() == cityId;

		List<JobPosting> result = new ArrayList<JobPosting>();

		if (workingTimeId == 0 && workingTypeId != 0 && cityId != 0) {
			stream.filter(workingTypeCondition).filter(cityCondition).forEach(jobPosting -> result.add(jobPosting));
		} else if (workingTimeId != 0 && workingTypeId == 0 && cityId != 0) {
			stream.filter(workingTimeCondition).filter(cityCondition).forEach(jobPosting -> result.add(jobPosting));
		} else if (workingTimeId != 0 && workingTypeId != 0 && cityId == 0) {
			stream.filter(workingTimeCondition).filter(workingTypeCondition).forEach(jobPosting -> result.add(jobPosting));
		} else if (workingTimeId == 0 && workingTypeId == 0 && cityId != 0) {
			stream.filter(cityCondition).forEach(jobPosting -> result.add(jobPosting));
		} else if (workingTimeId == 0 && workingTypeId != 0 && cityId == 0) {
			stream.filter(workingTypeCondition).forEach(jobPosting -> result.add(jobPosting));
		} else if (workingTimeId != 0 && workingTypeId == 0 && cityId == 0) {
			stream.filter(workingTimeCondition).forEach(jobPosting -> result.add(jobPosting));
		} else if (workingTimeId != 0 && workingTypeId != 0 && cityId != 0) {
			stream.filter(workingTimeCondition).filter(workingTypeCondition).filter(cityCondition).forEach(jobPosting -> result.add(jobPosting));
		} else {
			return getAllActiveOnes().getData();
		}

		return result;
	}

}
