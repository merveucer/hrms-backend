package io.kodlama.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import io.kodlama.hrms.entities.concretes.JobPostingConfirmation;

public interface JobPostingConfirmationDao extends JpaRepository<JobPostingConfirmation, Integer>{
	
	JobPostingConfirmation getByJobPosting_Id(int jobPostingId);

}
