package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kodlama.hrms.business.abstracts.EmployerService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.Employer;

@RestController
@RequestMapping("/api/employers")
@CrossOrigin
public class EmployersController {

	private EmployerService employerService;

	@Autowired
	public EmployersController(EmployerService employerService) {
		this.employerService = employerService;
	}

	@PutMapping("/update")
	public Result update(@RequestBody Employer employer) {
		return employerService.update(employer);
	}

	@GetMapping("/getAll")
	public DataResult<List<Employer>> getAll() {
		return employerService.getAll();
	}

	@GetMapping("getById")
	public DataResult<Employer> getById(@RequestParam int id) {
		return employerService.getById(id);
	}

	@PutMapping("/activate")
	public Result activate(@RequestParam String code) {
		return employerService.activate(code);
	}

	@PutMapping("/confirm")
	public Result confirm(@RequestParam int employerId, @RequestParam int companyStaffId, @RequestParam boolean isConfirmed) {
		return employerService.confirm(employerId, companyStaffId, isConfirmed);
	}

	@GetMapping("/getAllByIsActivatedAndIsConfirmed")
	public DataResult<List<Employer>> getAllByIsActivatedAndIsConfirmed(@RequestParam boolean isActivated, @RequestParam boolean isConfirmed) {
		return employerService.getAllByIsActivatedAndIsConfirmed(isActivated, isConfirmed);
	}

}
