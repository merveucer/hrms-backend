package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.kodlama.hrms.business.abstracts.JobTitleService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.entities.concretes.JobTitle;

@RestController
@RequestMapping("/api/job-titles")
public class JobTitlesController {

	private JobTitleService jobTitleService;

	@Autowired
	public JobTitlesController(JobTitleService jobTitleService) {
		super();
		this.jobTitleService = jobTitleService;
	}

	@GetMapping("/getall")
	public DataResult<List<JobTitle>> getAll() {
		return this.jobTitleService.getAll();
	}

}
