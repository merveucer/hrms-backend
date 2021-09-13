package io.kodlama.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import io.kodlama.hrms.entities.concretes.FavoriteJobPosting;

public interface FavoriteJobPostingDao extends JpaRepository<FavoriteJobPosting, Integer> {

	List<FavoriteJobPosting> getByCandidate_Id(int candidateId);

	List<FavoriteJobPosting> getByCandidate_Id(int candidateId, Sort sort);
	
	FavoriteJobPosting getByCandidate_IdAndJobPosting_Id(int candidateId, int jobPostingId);

}
