package io.kodlama.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.AuthService;
import io.kodlama.hrms.business.abstracts.CandidateService;
import io.kodlama.hrms.business.abstracts.CompanyStaffService;
import io.kodlama.hrms.business.abstracts.EmployerService;
import io.kodlama.hrms.core.entities.User;
import io.kodlama.hrms.core.utilities.results.ErrorResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.Candidate;
import io.kodlama.hrms.entities.concretes.CompanyStaff;
import io.kodlama.hrms.entities.concretes.Employer;

@Service
public class AuthManager implements AuthService {

	private CompanyStaffService companyStaffService;
	private CandidateService candidateService;
	private EmployerService employerService;

	@Autowired
	public AuthManager(CompanyStaffService companyStaffService, CandidateService candidateService, EmployerService employerService) {
		this.companyStaffService = companyStaffService;
		this.candidateService = candidateService;
		this.employerService = employerService;
	}
	
	@Override
	public Result resgisterCompanyStaff(CompanyStaff user, String confirmPassword) {
		
		validateUser(user, confirmPassword);

		return companyStaffService.add(user);
	}

	@Override
	public Result resgisterCandidate(Candidate user, String confirmPassword) {

		validateUser(user, confirmPassword);

		return candidateService.add(user);
	}

	@Override
	public Result resgisterEmployer(Employer user, String confirmPassword) {

		validateUser(user, confirmPassword);

		return employerService.add(user);
	}

	private boolean checkIfPasswordsMatch(String password, String confirmPassword) {

		boolean result = false;

		if (password.equals(confirmPassword)) {
			result = true;
		}

		return result;
	}

	private Result validateUser(User user, String confirmPassword) {

		if (!checkIfPasswordsMatch(user.getPassword(), confirmPassword)) {
			return new ErrorResult("Parola eşleşmesi gerçekleşmedi. Lütfen kontrol ederek yeniden deneyiniz.");
		}

		return null;
	}

}
