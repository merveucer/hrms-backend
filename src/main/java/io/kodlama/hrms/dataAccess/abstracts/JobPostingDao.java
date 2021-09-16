package io.kodlama.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import io.kodlama.hrms.entities.concretes.JobPosting;

public interface JobPostingDao extends JpaRepository<JobPosting, Integer> {
	
	List<JobPosting> getByIsActive(boolean isActive);

	List<JobPosting> getByIsActive(boolean isActive, Sort sort);
	
	List<JobPosting> getByIsActive(boolean isActive, Pageable pageable);

	List<JobPosting> getByIsActiveAndEmployer_Id(boolean isActive, int employerId, Sort sort);

}
