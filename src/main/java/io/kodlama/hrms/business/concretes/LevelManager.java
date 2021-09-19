package io.kodlama.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.LevelService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.LevelDao;
import io.kodlama.hrms.entities.concretes.Level;

@Service
public class LevelManager implements LevelService {
	
	private LevelDao levelDao;

	@Autowired
	public LevelManager(LevelDao levelDao) {
		this.levelDao = levelDao;
	}

	@Override
	public Result add(Level level) {
		
		levelDao.save(level);
		return new SuccessResult();
	}

	@Override
	public Result update(Level level) {
		
		levelDao.save(level);
		return new SuccessResult();
	}

	@Override
	public Result delete(int id) {
		
		levelDao.deleteById(id);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<Level>> getAll() {
		
		Sort sort = Sort.by(Sort.Direction.ASC, "level");
		
		return new SuccessDataResult<List<Level>>(levelDao.findAll(sort));
	}

	@Override
	public DataResult<Level> getById(int id) {
		return new SuccessDataResult<Level>(levelDao.getById(id));
	}

}
