package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.entities.concretes.Experience;

public interface ExperienceService extends BaseEntityService<Experience> {

	DataResult<List<Experience>> getAllByResumeId(int resumeId);

	DataResult<List<Experience>> getAllByResumeIdSortedByTerminationDate(int resumeId);

}
