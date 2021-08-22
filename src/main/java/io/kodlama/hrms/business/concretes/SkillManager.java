package io.kodlama.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.SkillService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.SkillDao;
import io.kodlama.hrms.entities.concretes.Skill;

@Service
public class SkillManager implements SkillService {

	private SkillDao skillDao;

	@Autowired
	public SkillManager(SkillDao skillDao) {
		this.skillDao = skillDao;
	}

	@Override
	public Result add(Skill skill) {

		skillDao.save(skill);
		return new SuccessResult("Yetenek eklendi.");
	}

	@Override
	public Result update(Skill skill) {

		skillDao.save(skill);
		return new SuccessResult("Yetenek güncellendi.");
	}

	@Override
	public Result delete(int id) {

		skillDao.deleteById(id);
		return new SuccessResult("Yetenek silindi.");
	}

	@Override
	public DataResult<List<Skill>> getAll() {
		return new SuccessDataResult<List<Skill>>(skillDao.findAll());
	}

	@Override
	public DataResult<Skill> getById(int id) {
		return new SuccessDataResult<Skill>(skillDao.getById(id));
	}

}
