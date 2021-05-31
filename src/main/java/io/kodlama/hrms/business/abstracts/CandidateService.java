package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.Candidate;
import io.kodlama.hrms.entities.concretes.UserActivation;

public interface CandidateService {
	
	Result add(Candidate candidate);
	
	Result update(Candidate candidate);
	
	Result delete(Candidate candidate);
	
	DataResult<List<Candidate>> getAll();
	
	DataResult<Candidate> getById(int id);
	
	DataResult<Candidate> getByIdentityNumber(String identityNumber);
	
	Result activate(UserActivation userActivation);

}
