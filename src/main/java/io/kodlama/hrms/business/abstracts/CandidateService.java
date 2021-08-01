package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.Candidate;

public interface CandidateService extends BaseEntityService<Candidate> {

	Result activate(String code);

	DataResult<List<Candidate>> getAllByIsActivated(boolean isActivated);

	DataResult<Candidate> getByIdentityNumber(String identityNumber);

}
