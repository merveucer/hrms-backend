package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping("/delete")
	public Result delete(@RequestBody JobPosting jobPosting) {
		return jobPostingService.delete(jobPosting);
	}

	@GetMapping("/getAll")
	public DataResult<List<JobPosting>> getAll() {
		return jobPostingService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<JobPosting> getById(@RequestParam int id) {
		return jobPostingService.getById(id);
	}

	@PostMapping("/confirm")
	public Result confirm(@RequestParam int jobPostingId, @RequestParam int companyStaffId,	@RequestParam boolean isConfirmed) {
		return jobPostingService.confirm(jobPostingId, companyStaffId, isConfirmed);
	}

	@PostMapping("/doActiveOrPassive")
	public Result doActiveOrPassive(@RequestParam int id, @RequestParam boolean isActive) {
		return jobPostingService.doActiveOrPassive(id, isActive);
	}

	@GetMapping("/getAllActiveJobPosting")
	public DataResult<List<JobPosting>> getAllActiveJobPosting(@RequestParam int pageNo, @RequestParam int pageSize) {
		return jobPostingService.getAllActiveJobPosting(pageNo, pageSize);
	}

	@GetMapping("/getAllActiveJobPostingSortedByPostingDate")
	public DataResult<List<JobPosting>> getAllActiveJobPostingSortedByPostingDate(@RequestParam int pageNo,	@RequestParam int pageSize) {
		return jobPostingService.getAllActiveJobPostingSortedByPostingDate(pageNo, pageSize);
	}

	@GetMapping("/getAllActiveJobPostingSortedByPostingDateTop6")
	public DataResult<List<JobPosting>> getAllActiveJobPostingSortedByPostingDateTop6() {
		return jobPostingService.getAllActiveJobPostingSortedByPostingDateTop6();
	}

	@GetMapping("/getAllActiveJobPostingByEmployerId")
	public DataResult<List<JobPosting>> getAllActiveJobPostingByEmployerId(@RequestParam int employerId) {
		return jobPostingService.getAllActiveJobPostingByEmployerId(employerId);
	}

	@GetMapping("/getAllActiveJobPostingFilteredByWorkingTimeAndWorkingTypeAndCity")
	public DataResult<List<JobPosting>> getAllActiveJobPostingFilteredByWorkingTimeAndWorkingTypeAndCity(@RequestParam int workingTimeId,
			@RequestParam int workingTypeId, @RequestParam int cityId, @RequestParam int pageNo, @RequestParam int pageSize) {
		return jobPostingService.getAllActiveJobPostingFilteredByWorkingTimeAndWorkingTypeAndCity(workingTimeId, workingTypeId, cityId, pageNo, pageSize);
	}

}
