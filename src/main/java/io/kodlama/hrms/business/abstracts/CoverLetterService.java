package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.entities.concretes.CoverLetter;

public interface CoverLetterService extends BaseEntityService<CoverLetter> {

	DataResult<List<CoverLetter>> getAllByCandidateId(int candidateId);

}
