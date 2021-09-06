package io.kodlama.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import io.kodlama.hrms.entities.concretes.JobPostingConfirmationType;

public interface JobPostingConfirmationTypeDao extends JpaRepository<JobPostingConfirmationType, Integer> {

}
