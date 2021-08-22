package io.kodlama.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.FavoriteJobPostingService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.FavoriteJobPostingDao;
import io.kodlama.hrms.entities.concretes.FavoriteJobPosting;

@Service
public class FavoriteJobPostingManager implements FavoriteJobPostingService {

	private FavoriteJobPostingDao favoriteJobPostingDao;

	@Autowired
	public FavoriteJobPostingManager(FavoriteJobPostingDao favoriteJobPostingDao) {
		this.favoriteJobPostingDao = favoriteJobPostingDao;
	}

	@Override
	public Result add(FavoriteJobPosting favoriteJobPosting) {

		favoriteJobPosting.setDateOfAddToFavorites(LocalDateTime.now());

		favoriteJobPostingDao.save(favoriteJobPosting);
		return new SuccessResult();
	}

	@Override
	public Result update(FavoriteJobPosting favoriteJobPosting) {
		
		favoriteJobPostingDao.save(favoriteJobPosting);
		return new SuccessResult();
	}

	@Override
	public Result delete(int id) {
		
		favoriteJobPostingDao.deleteById(id);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<FavoriteJobPosting>> getAll() {
		return new SuccessDataResult<List<FavoriteJobPosting>>(favoriteJobPostingDao.findAll());
	}

	@Override
	public DataResult<FavoriteJobPosting> getById(int id) {
		return new SuccessDataResult<FavoriteJobPosting>(favoriteJobPostingDao.getById(id));
	}

	@Override
	public DataResult<List<FavoriteJobPosting>> getAllByCandidateId(int candidateId) {
		return new SuccessDataResult<List<FavoriteJobPosting>>(favoriteJobPostingDao.getByCandidate_Id(candidateId));
	}

	@Override
	public DataResult<List<FavoriteJobPosting>> getAllByCandidateIdSortedByDateOfAddToFavorites(int candidateId) {

		Sort sort = Sort.by(Sort.Direction.DESC, "dateOfAddToFavorites");

		return new SuccessDataResult<List<FavoriteJobPosting>>(favoriteJobPostingDao.getByCandidate_Id(candidateId, sort));
	}

}
