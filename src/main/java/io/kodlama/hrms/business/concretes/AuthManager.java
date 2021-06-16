package io.kodlama.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.AuthService;
import io.kodlama.hrms.business.abstracts.CandidateService;
import io.kodlama.hrms.business.abstracts.EmployerService;
import io.kodlama.hrms.business.abstracts.UserService;
import io.kodlama.hrms.core.utilities.results.ErrorResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.Candidate;
import io.kodlama.hrms.entities.concretes.Employer;

@Service
public class AuthManager implements AuthService {

	private UserService userService;
	private CandidateService candidateService;
	private EmployerService employerService;

	@Autowired
	public AuthManager(UserService userService, CandidateService candidateService, EmployerService employerService) {
		this.userService = userService;
		this.candidateService = candidateService;
		this.employerService = employerService;
	}

	@Override
	public Result resgisterCandidate(Candidate candidate, String confirmPassword) {

		if (!checkIfEmailExists(candidate.getEmail())) {
			return new ErrorResult("Girilen e-posta adresi başka bir hesaba aittir.");
		}

		if (!checkIfPasswordsMatch(candidate.getPassword(), confirmPassword)) {
			return new ErrorResult("Parola eşleşmesi gerçekleşmedi. Lütfen kontrol ederek yeniden deneyiniz.");
		}

		return candidateService.add(candidate);
	}

	@Override
	public Result resgisterEmployer(Employer employer, String confirmPassword) {

		if (!checkIfEmailExists(employer.getEmail())) {
			return new ErrorResult("Girilen e-posta adresi başka bir hesaba aittir.");
		}

		if (!checkIfPasswordsMatch(employer.getPassword(), confirmPassword)) {
			return new ErrorResult("Parola eşleşmesi gerçekleşmedi. Lütfen kontrol ederek yeniden deneyiniz.");
		}

		return employerService.add(employer);
	}

	private boolean checkIfEmailExists(String email) {

		boolean result = false;

		if (userService.getByEmail(email).getData() == null) {
			result = true;
		}

		return result;
	}

	private boolean checkIfPasswordsMatch(String password, String confirmPassword) {

		boolean result = false;

		if (password.equals(confirmPassword)) {
			result = true;
		}

		return result;
	}

}
