package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kodlama.hrms.business.abstracts.EmployerService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.Employer;

@RestController
@RequestMapping("/api/employers")
public class EmployersController {

	private EmployerService employerService;

	@Autowired
	public EmployersController(EmployerService employerService) {
		this.employerService = employerService;
	}

	@GetMapping("/getAll")
	public DataResult<List<Employer>> getAll() {
		return employerService.getAll();
	}
	
	@GetMapping("getById")
	public DataResult<Employer> getById(@RequestParam int id) {
		return employerService.getById(id);
	}
	
	@GetMapping("/getByIsActivatedAndIsConfirmed")
	public DataResult<List<Employer>> getByIsActivatedAndIsConfirmed(boolean isActivated, boolean isConfirmed) {
		return employerService.getByIsActivatedAndIsConfirmed(isActivated, isConfirmed);
	}
	
	@PostMapping("/activate")
	public Result activate(String code) {
		return employerService.activate(code);
	}
	
	@PostMapping("/confirm")
	public Result confirm(int employerId, int companyStaffId, boolean isConfirmed) {
		return employerService.confirm(employerId, companyStaffId, isConfirmed);
	}

}
