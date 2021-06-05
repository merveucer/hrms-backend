package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kodlama.hrms.business.abstracts.UserConfirmationService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.UserConfirmation;

@RestController
@RequestMapping("/api/userConfirmations")
public class UserConfirmationsController {
	
	private UserConfirmationService userConfirmationService;

	@Autowired
	public UserConfirmationsController(UserConfirmationService userConfirmationService) {
		this.userConfirmationService = userConfirmationService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody UserConfirmation userConfirmation) {
		return userConfirmationService.add(userConfirmation);
	}

	@PostMapping("/update")
	public Result update(@RequestBody UserConfirmation userConfirmation) {
		return userConfirmationService.update(userConfirmation);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody UserConfirmation userConfirmation) {
		return userConfirmationService.delete(userConfirmation);
	}

	@GetMapping("/getAll")
	public DataResult<List<UserConfirmation>> getAll() {
		return userConfirmationService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<UserConfirmation> getById(@RequestParam int id) {
		return userConfirmationService.getById(id);
	}

}
