package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.entities.concretes.Education;

public interface EducationService extends BaseEntityService<Education> {

	DataResult<List<Education>> getAllByResumeId(int resumeId);

	DataResult<List<Education>> getAllByResumeIdSortedByGraduationDate(int resumeId);

}
