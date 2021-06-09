package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import io.kodlama.hrms.entities.dtos.JobPostingWithEmployerAndJobTitleDto;

@RestController
@RequestMapping("/api/jobPostings")
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

	@PostMapping("/update")
	public Result update(@RequestBody JobPosting jobPosting) {
		return jobPostingService.update(jobPosting);
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
	
	@GetMapping("/getAllActiveJobPostingDetails")
	public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetails() {
		return jobPostingService.getAllActiveJobPostingDetails();
	}
	
	@GetMapping("/getAllActiveJobPostingDetailsSortedByPostingDate")
	public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetailsSortedByPostingDate() {
		return jobPostingService.getAllActiveJobPostingDetailsSortedByPostingDate();
	}
	
	@GetMapping("/getAllActiveJobPostingDetailsByCompanyName")
	public DataResult<List<JobPostingWithEmployerAndJobTitleDto>> getAllActiveJobPostingDetailsByCompanyName(String companyName) {
		return jobPostingService.getAllActiveJobPostingDetailsByCompanyName(companyName);
	}

}
