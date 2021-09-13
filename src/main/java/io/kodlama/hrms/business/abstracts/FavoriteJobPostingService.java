package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.entities.concretes.FavoriteJobPosting;
import io.kodlama.hrms.entities.concretes.JobPosting;

public interface FavoriteJobPostingService extends BaseEntityService<FavoriteJobPosting> {
	
	DataResult<List<FavoriteJobPosting>> getAllByCandidateId(int candidateId);

	DataResult<List<JobPosting>> getAllActiveJobPostingsByCandidateIdSortedByDateOfAddToFavorites(int candidateId);
	
	DataResult<FavoriteJobPosting> getByCandidateIdAndJobPostingId(int candidateId, int jobPostingId);

}
