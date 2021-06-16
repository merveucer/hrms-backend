package io.kodlama.hrms.business.abstracts;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.Candidate;

public interface CandidateService extends BaseEntityService<Candidate> {
	
	Result activate(String code);

	DataResult<Candidate> getByIdentityNumber(String identityNumber);

}
