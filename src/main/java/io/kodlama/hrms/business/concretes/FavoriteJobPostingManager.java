package io.kodlama.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.FavoriteJobPostingService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.ErrorResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.FavoriteJobPostingDao;
import io.kodlama.hrms.entities.concretes.FavoriteJobPosting;
import io.kodlama.hrms.entities.concretes.JobPosting;

@Service
public class FavoriteJobPostingManager implements FavoriteJobPostingService {

	private FavoriteJobPostingDao favoriteJobPostingDao;

	@Autowired
	public FavoriteJobPostingManager(FavoriteJobPostingDao favoriteJobPostingDao) {
		this.favoriteJobPostingDao = favoriteJobPostingDao;
	}

	@Override
	public Result add(FavoriteJobPosting favoriteJobPosting) {
		
		if (getByCandidateIdAndJobPostingId(favoriteJobPosting.getCandidate().getId(), favoriteJobPosting.getJobPosting().getId()).getData() == null) {
			favoriteJobPosting.setDateOfAddToFavorites(LocalDateTime.now());
			favoriteJobPostingDao.save(favoriteJobPosting);
			
			return new SuccessResult("İlan favorilere eklendi.");
		}
		
		return new ErrorResult();
	}

	@Override
	public Result update(FavoriteJobPosting favoriteJobPosting) {
		
		favoriteJobPostingDao.save(favoriteJobPosting);
		return new SuccessResult();
	}

	@Override
	public Result delete(int id) {
		
		favoriteJobPostingDao.deleteById(id);
		return new SuccessResult("İlan favorilerden kaldırıldı.");
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
	public DataResult<List<JobPosting>> getAllActiveJobPostingsByCandidateIdSortedByDateOfAddToFavorites(int candidateId) {
		
		List<JobPosting> jobPostings = new ArrayList<JobPosting>();

		Sort sort = Sort.by(Sort.Direction.DESC, "dateOfAddToFavorites");
		
		for (FavoriteJobPosting favoriteJobPosting : favoriteJobPostingDao.getByCandidate_Id(candidateId, sort)) {
			if (favoriteJobPosting.getJobPosting().isActive()) {
				jobPostings.add(favoriteJobPosting.getJobPosting());
			} else {
				delete(favoriteJobPosting.getId());
			}
		}		
		
		return new SuccessDataResult<List<JobPosting>>(jobPostings);
	}

	@Override
	public DataResult<FavoriteJobPosting> getByCandidateIdAndJobPostingId(int candidateId, int jobPostingId) {
		return new SuccessDataResult<FavoriteJobPosting>(favoriteJobPostingDao.getByCandidate_IdAndJobPosting_Id(candidateId, jobPostingId));
	}

}
