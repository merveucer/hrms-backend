package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kodlama.hrms.business.abstracts.UpdatedEmployerService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.entities.concretes.UpdatedEmployer;

@RestController
@RequestMapping("/api/updatedEmployers")
@CrossOrigin
class UpdatedEmployersController {
	
	private UpdatedEmployerService updatedEmployerService;

	@Autowired
	public UpdatedEmployersController(UpdatedEmployerService updatedEmployerService) {
		this.updatedEmployerService = updatedEmployerService;
	}

	@GetMapping("/getAll")
	public DataResult<List<UpdatedEmployer>> getAll() {
		return updatedEmployerService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<UpdatedEmployer> getById(@RequestParam int id) {
		return updatedEmployerService.getById(id);
	}
	
	@GetMapping("/getByEmployerId")
	public DataResult<UpdatedEmployer> getByEmployerId(@RequestParam int employerId) {
		return updatedEmployerService.getByEmployerId(employerId);
	}

}
