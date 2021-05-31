package io.kodlama.hrms.business.concretes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import io.kodlama.hrms.entities.concretes.User;

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

		if (!checkIfEmailIsValid(candidate.getEmail())) {
			return new ErrorResult("Lütfen geçerli bir e-posta adresi giriniz.");
		}

		if (checkIfEmailExists(candidate.getEmail())) {
			return new ErrorResult("Girilen e-posta adresi başka bir hesaba aittir.");
		}

		if (!checkIfPasswordsMatch(candidate.getPassword(), confirmPassword)) {
			return new ErrorResult("Parola eşleşmesi gerçekleşmedi. Lütfen kontrol ederek yeniden deneyiniz.");
		}

		return candidateService.add(candidate);
	}

	@Override
	public Result resgisterEmployer(Employer employer, String confirmPassword) {

		if (!checkIfEmailIsValid(employer.getEmail())) {
			return new ErrorResult("Lütfen geçerli bir e-posta adresi giriniz.");
		}

		if (checkIfEmailExists(employer.getEmail())) {
			return new ErrorResult("Girilen e-posta adresi başka bir hesaba aittir.");
		}

		if (!checkIfPasswordsMatch(employer.getPassword(), confirmPassword)) {
			return new ErrorResult("Parola eşleşmesi gerçekleşmedi. Lütfen kontrol ederek yeniden deneyiniz.");
		}

		return employerService.add(employer);
	}

	private boolean checkIfEmailIsValid(String email) {

		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	private boolean checkIfEmailExists(String email) {

		boolean result = false;

		for (User user : userService.getAll().getData()) {
			if (user.getEmail() == email) {
				result = true;
			}
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
