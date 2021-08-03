package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.entities.concretes.FavoriteJobPosting;

public interface FavoriteJobPostingService extends BaseEntityService<FavoriteJobPosting> {
	
	DataResult<List<FavoriteJobPosting>> getAllByCandidateId(int candidateId);

	DataResult<List<FavoriteJobPosting>> getAllByCandidateIdSortedByDateOfAddToFavorites(int candidateId);

}
