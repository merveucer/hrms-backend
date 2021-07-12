package io.kodlama.hrms.business.abstracts;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.entities.concretes.CoverLetter;

public interface CoverLetterService extends BaseEntityService<CoverLetter> {
	
	DataResult<CoverLetter> getByCandidateId(int candidateId);

}
