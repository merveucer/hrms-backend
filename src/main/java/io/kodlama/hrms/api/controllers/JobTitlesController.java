package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kodlama.hrms.business.abstracts.JobTitleService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.JobTitle;

@RestController
@RequestMapping("/api/jobTitles")
public class JobTitlesController {

	private JobTitleService jobTitleService;

	@Autowired
	public JobTitlesController(JobTitleService jobTitleService) {
		super();
		this.jobTitleService = jobTitleService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobTitle jobTitle) {
		return this.jobTitleService.add(jobTitle);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody JobTitle jobTitle) {
		return this.jobTitleService.update(jobTitle);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody JobTitle jobTitle) {
		return this.jobTitleService.delete(jobTitle);
	}

	@GetMapping("/getAll")
	public DataResult<List<JobTitle>> getAll() {
		return this.jobTitleService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<JobTitle> getById(@RequestParam int id) {
		return this.jobTitleService.getById(id);
	}

}
