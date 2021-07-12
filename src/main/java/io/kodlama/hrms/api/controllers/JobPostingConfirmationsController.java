package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kodlama.hrms.business.abstracts.JobPostingConfirmationService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.JobPostingConfirmation;

@RestController
@RequestMapping("/api/jobPostingConfirmations")
public class JobPostingConfirmationsController {
	
	private JobPostingConfirmationService jobPostingConfirmationService;

	@Autowired
	public JobPostingConfirmationsController(JobPostingConfirmationService jobPostingConfirmationService) {
		this.jobPostingConfirmationService = jobPostingConfirmationService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobPostingConfirmation jobPostingConfirmation) {
		return jobPostingConfirmationService.add(jobPostingConfirmation);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody JobPostingConfirmation jobPostingConfirmation) {
		return jobPostingConfirmationService.update(jobPostingConfirmation);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody JobPostingConfirmation jobPostingConfirmation) {
		return jobPostingConfirmationService.delete(jobPostingConfirmation);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<JobPostingConfirmation>> getAll() {
		return jobPostingConfirmationService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<JobPostingConfirmation> getById(@RequestParam int id) {
		return jobPostingConfirmationService.getById(id);
	}

}
