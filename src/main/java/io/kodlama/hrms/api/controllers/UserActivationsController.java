package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kodlama.hrms.business.abstracts.UserActivationService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.entities.concretes.UserActivation;

@RestController
@RequestMapping("/api/userActivations")
@CrossOrigin
public class UserActivationsController {

	private UserActivationService userActivationService;

	@Autowired
	public UserActivationsController(UserActivationService userActivationService) {
		this.userActivationService = userActivationService;
	}

	@GetMapping("/getAll")
	public DataResult<List<UserActivation>> getAll() {
		return userActivationService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<UserActivation> getById(@RequestParam int id) {
		return userActivationService.getById(id);
	}
	
	@GetMapping("/getByUserId")
	public DataResult<UserActivation> getByUserId(@RequestParam int userId) {
		return userActivationService.getByUserId(userId);
	}
	
	@GetMapping("/getAllByIsActivated")
	public DataResult<List<UserActivation>> getAllByIsActivated(@RequestParam boolean isActivated) {
		return userActivationService.getAllByIsActivated(isActivated);
	}

}
