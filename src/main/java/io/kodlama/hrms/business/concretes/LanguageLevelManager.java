package io.kodlama.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.LanguageLevelService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.LanguageLevelDao;
import io.kodlama.hrms.entities.concretes.LanguageLevel;

@Service
public class LanguageLevelManager implements LanguageLevelService {

	private LanguageLevelDao languageLevelDao;

	@Autowired
	public LanguageLevelManager(LanguageLevelDao languageLevelDao) {
		this.languageLevelDao = languageLevelDao;
	}

	@Override
	public Result add(LanguageLevel languageLevel) {

		languageLevelDao.save(languageLevel);
		return new SuccessResult("Dil seviyesi eklendi.");
	}

	@Override
	public Result update(LanguageLevel languageLevel) {

		languageLevelDao.save(languageLevel);
		return new SuccessResult("Dil seviyesi güncellendi.");
	}

	@Override
	public Result delete(int id) {

		languageLevelDao.deleteById(id);
		return new SuccessResult("Dil seviyesi silindi.");
	}

	@Override
	public DataResult<List<LanguageLevel>> getAll() {
		return new SuccessDataResult<List<LanguageLevel>>(languageLevelDao.findAll());
	}

	@Override
	public DataResult<LanguageLevel> getById(int id) {
		return new SuccessDataResult<LanguageLevel>(languageLevelDao.getById(id));
	}

}
