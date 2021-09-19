package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kodlama.hrms.business.abstracts.JobPostingConfirmationService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.entities.concretes.JobPostingConfirmation;

@RestController
@RequestMapping("/api/jobPostingConfirmations")
@CrossOrigin
public class JobPostingConfirmationsController {

	private JobPostingConfirmationService jobPostingConfirmationService;

	@Autowired
	public JobPostingConfirmationsController(JobPostingConfirmationService jobPostingConfirmationService) {
		this.jobPostingConfirmationService = jobPostingConfirmationService;
	}

	@GetMapping("/getAll")
	public DataResult<List<JobPostingConfirmation>> getAll() {
		return jobPostingConfirmationService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<JobPostingConfirmation> getById(@RequestParam int id) {
		return jobPostingConfirmationService.getById(id);
	}
	
	@GetMapping("/getByJobPostingId")
	public DataResult<JobPostingConfirmation> getByJobPostingId(@RequestParam int jobPostingId) {
		return jobPostingConfirmationService.getByJobPostingId(jobPostingId);
	}

}
