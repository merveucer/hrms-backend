package io.kodlama.hrms.business.abstracts;

import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.Candidate;
import io.kodlama.hrms.entities.concretes.CompanyStaff;
import io.kodlama.hrms.entities.concretes.Employer;

public interface AuthService {
	
	Result resgisterCompanyStaff(CompanyStaff companyStaff, String confirmPassword);

	Result resgisterCandidate(Candidate candidate, String confirmPassword);

	Result resgisterEmployer(Employer employer, String confirmPassword);

}
