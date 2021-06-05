package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kodlama.hrms.business.abstracts.UserActivationService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.UserActivation;

@RestController
@RequestMapping("/api/userActivations")
public class UserActivationsController {
	
	private UserActivationService userActivationService;

	@Autowired
	public UserActivationsController(UserActivationService userActivationService) {
		this.userActivationService = userActivationService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody UserActivation userActivation) {
		return userActivationService.add(userActivation);
	}

	@PostMapping("/update")
	public Result update(@RequestBody UserActivation userActivation) {
		return userActivationService.update(userActivation);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody UserActivation userActivation) {
		return userActivationService.delete(userActivation);
	}

	@GetMapping("/getAll")
	public DataResult<List<UserActivation>> getAll() {
		return userActivationService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<UserActivation> getById(@RequestParam int id) {
		return userActivationService.getById(id);
	}

}
