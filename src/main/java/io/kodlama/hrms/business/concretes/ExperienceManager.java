package io.kodlama.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.ExperienceService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.ExperienceDao;
import io.kodlama.hrms.entities.concretes.Experience;

@Service
public class ExperienceManager implements ExperienceService {

	private ExperienceDao experienceDao;

	@Autowired
	public ExperienceManager(ExperienceDao experienceDao) {
		this.experienceDao = experienceDao;
	}

	@Override
	public Result add(Experience experience) {

		experienceDao.save(experience);
		return new SuccessResult("İş deneyimi eklendi.");
	}

	@Override
	public Result update(Experience experience) {

		experienceDao.save(experience);
		return new SuccessResult("İş deneyimi güncellendi.");
	}

	@Override
	public Result delete(int id) {

		experienceDao.deleteById(id);
		return new SuccessResult("İş deneyimi silindi.");
	}

	@Override
	public DataResult<List<Experience>> getAll() {
		return new SuccessDataResult<List<Experience>>(experienceDao.findAll());
	}

	@Override
	public DataResult<Experience> getById(int id) {
		return new SuccessDataResult<Experience>(experienceDao.getById(id));
	}

	@Override
	public DataResult<List<Experience>> getAllByResumeId(int resumeId) {
		return new SuccessDataResult<List<Experience>>(experienceDao.getByResume_Id(resumeId));
	}

	@Override
	public DataResult<List<Experience>> getAllByResumeIdSortedByTerminationDate(int resumeId) {

		Sort sort = Sort.by(Sort.Direction.DESC, "terminationDate");

		return new SuccessDataResult<List<Experience>>(experienceDao.getByResume_Id(resumeId, sort));
	}

}
