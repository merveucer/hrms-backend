package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.Resume;
import io.kodlama.hrms.entities.dtos.ResumeWithAllRelatedEntitiesDto;

public interface ResumeService extends BaseEntityService<Resume> {

	Result addCoverLetterToResume(int resumeId, int coverLetterId);
	
	Result deleteCoverLetterFromResume(int resumeId);

	DataResult<List<ResumeWithAllRelatedEntitiesDto>> getAllResumesDetailsByActivatedCandidate();

	DataResult<Resume> getByCandidateId(int candidateId);

	DataResult<ResumeWithAllRelatedEntitiesDto> getResumeDetailsByCandidateId(int candidateId);

}
