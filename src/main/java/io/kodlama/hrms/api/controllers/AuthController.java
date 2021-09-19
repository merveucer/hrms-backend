package io.kodlama.hrms.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.kodlama.hrms.business.abstracts.AuthService;
import io.kodlama.hrms.core.utilities.results.ErrorDataResult;
import io.kodlama.hrms.entities.concretes.Candidate;
import io.kodlama.hrms.entities.concretes.CompanyStaff;
import io.kodlama.hrms.entities.concretes.Employer;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

	private AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/registerCompanyStaff")
	public ResponseEntity<?> registerCompanyStaff(@Valid @RequestBody CompanyStaff companyStaff, String confirmPassword) {
		return ResponseEntity.ok(authService.resgisterCompanyStaff(companyStaff, confirmPassword));
	}

	@PostMapping("/registerCandidate")
	public ResponseEntity<?> registerCandidate(@Valid @RequestBody Candidate candidate, String confirmPassword) {
		return ResponseEntity.ok(authService.resgisterCandidate(candidate, confirmPassword));
	}

	@PostMapping("/registerEmployer")
	public ResponseEntity<?> registerEmployer(@Valid @RequestBody Employer employer, String confirmPassword) {
		return ResponseEntity.ok(authService.resgisterEmployer(employer, confirmPassword));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {

		Map<String, String> validationErrors = new HashMap<String, String>();

		for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors);

		return errors;
	}

}
