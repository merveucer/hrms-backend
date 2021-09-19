package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kodlama.hrms.business.abstracts.JobPostingService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.JobPosting;

@RestController
@RequestMapping("/api/jobPostings")
@CrossOrigin
public class JobPostingsController {

	private JobPostingService jobPostingService;

	@Autowired
	public JobPostingsController(JobPostingService jobPostingService) {
		this.jobPostingService = jobPostingService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody JobPosting jobPosting) {
		return jobPostingService.add(jobPosting);
	}

	@GetMapping("/getAll")
	public DataResult<List<JobPosting>> getAll() {
		return jobPostingService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<JobPosting> getById(@RequestParam int id) {
		return jobPostingService.getById(id);
	}

	@PutMapping("/confirm")
	public Result confirm(@RequestParam int jobPostingId, @RequestParam int companyStaffId, @RequestParam int jobPostingConfirmationTypeId, @RequestParam boolean isConfirmed) {
		return jobPostingService.confirm(jobPostingId, companyStaffId, jobPostingConfirmationTypeId, isConfirmed);
	}

	@PutMapping("/makeActiveOrPassive")
	public Result makeActiveOrPassive(@RequestParam int id, @RequestParam boolean isActive) {
		return jobPostingService.makeActiveOrPassive(id, isActive);
	}
	
	@GetMapping("/getAllByIsActive")
	public DataResult<List<JobPosting>> getAllByIsActive(@RequestParam boolean isActive) {
		return jobPostingService.getAllByIsActive(isActive);
	}

	@GetMapping("/getAllActiveOnesByPage")
	public DataResult<List<JobPosting>> getAllActiveOnesByPage(@RequestParam int pageNo, @RequestParam int pageSize) {
		return jobPostingService.getAllActiveOnesByPage(pageNo, pageSize);
	}
	
	@GetMapping("/getAllActiveOnesSortedByPostingDate")
	public DataResult<List<JobPosting>> getAllActiveOnesSortedByPostingDate() {
		return jobPostingService.getAllActiveOnesSortedByPostingDate();
	}

	@GetMapping("/getAllActiveOnesByPageSortedByPostingDate")
	public DataResult<List<JobPosting>> getAllActiveOnesByPageSortedByPostingDate(@RequestParam int pageNo,	@RequestParam int pageSize) {
		return jobPostingService.getAllActiveOnesByPageSortedByPostingDate(pageNo, pageSize);
	}

	@GetMapping("/getAllActiveOnesSortedByPostingDateTop6")
	public DataResult<List<JobPosting>> getAllActiveOnesSortedByPostingDateTop6() {
		return jobPostingService.getAllActiveOnesSortedByPostingDateTop6();
	}

	@GetMapping("/getAllActiveOnesByEmployerIdSortedByPostingDate")
	public DataResult<List<JobPosting>> getAllActiveOnesByEmployerIdSortedByPostingDate(@RequestParam int employerId) {
		return jobPostingService.getAllActiveOnesByEmployerIdSortedByPostingDate(employerId);
	}
	
	@GetMapping("/getAllActiveOnesFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType")
	public DataResult<List<JobPosting>> getAllActiveOnesFilteredByWorkingTimeAndWorkingTypeAndCityAndJobTitle(@RequestParam int cityId, @RequestParam int jobTitleId,
			@RequestParam int workingTimeId, @RequestParam int workingTypeId) {
		return jobPostingService.getAllActiveOnesFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(cityId, jobTitleId, workingTimeId, workingTypeId);
	}

	@GetMapping("/getAllActiveOnesByPageFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType")
	public DataResult<List<JobPosting>> getAllActiveOnesByPageFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(@RequestParam int cityId, @RequestParam int jobTitleId,
			@RequestParam int workingTimeId, @RequestParam int workingTypeId, @RequestParam int pageNo, @RequestParam int pageSize) {
		return jobPostingService.getAllActiveOnesByPageFilteredByCityAndJobTitleAndWorkingTimeAndWorkingType(cityId, jobTitleId, workingTimeId, workingTypeId, pageNo, pageSize);
	}
	
	@GetMapping("/getAllOnesThatWaitingForPostingConfirmation")
	public DataResult<List<JobPosting>> getAllOnesThatWaitingForPostingConfirmation() {
		return jobPostingService.getAllOnesThatWaitingForPostingConfirmation();
	}

}
