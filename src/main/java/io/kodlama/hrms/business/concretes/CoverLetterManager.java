package io.kodlama.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.CoverLetterService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.CoverLetterDao;
import io.kodlama.hrms.entities.concretes.CoverLetter;

@Service
public class CoverLetterManager implements CoverLetterService {

	private CoverLetterDao coverLetterDao;

	@Autowired
	public CoverLetterManager(CoverLetterDao coverLetterDao) {
		this.coverLetterDao = coverLetterDao;
	}

	@Override
	public Result add(CoverLetter coverLetter) {

		coverLetterDao.save(coverLetter);
		return new SuccessResult("Ön yazı eklendi.");
	}

	@Override
	public Result update(CoverLetter coverLetter) {

		coverLetterDao.save(coverLetter);
		return new SuccessResult("Ön yazı güncellendi.");
	}

	@Override
	public Result delete(int id) {

		coverLetterDao.deleteById(id);
		return new SuccessResult("Ön yazı silindi.");
	}

	@Override
	public DataResult<List<CoverLetter>> getAll() {
		return new SuccessDataResult<List<CoverLetter>>(coverLetterDao.findAll());
	}

	@Override
	public DataResult<CoverLetter> getById(int id) {
		return new SuccessDataResult<CoverLetter>(coverLetterDao.getById(id));
	}

	@Override
	public DataResult<List<CoverLetter>> getAllByCandidateId(int candidateId) {
		return new SuccessDataResult<List<CoverLetter>>(coverLetterDao.getByCandidate_Id(candidateId));
	}

}
