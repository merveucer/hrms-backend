package io.kodlama.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.kodlama.hrms.entities.concretes.JobPosting;
import io.kodlama.hrms.entities.dtos.JobPostingWithEmployerAndJobTitleDto;

public interface JobPostingDao extends JpaRepository<JobPosting, Integer> {

	@Query("Select new io.kodlama.hrms.entities.dtos.JobPostingWithEmployerAndJobTitleDto"
			+ "(jp.id, e.companyName, jt.title, jp.numberOfOpenPositions, jp.postingDate, jp.closingDate, jp.isActive)"
			+ "From JobPosting jp Inner Join jp.employer e Inner Join jp.jobTitle jt Where jp.isActive=:isActive")
	List<JobPostingWithEmployerAndJobTitleDto> getJobPostingWithEmployerAndJobTitleDtoByIsActive(boolean isActive);

	@Query("Select new io.kodlama.hrms.entities.dtos.JobPostingWithEmployerAndJobTitleDto"
			+ "(jp.id, e.companyName, jt.title, jp.numberOfOpenPositions, jp.postingDate, jp.closingDate, jp.isActive)"
			+ "From JobPosting jp Inner Join jp.employer e Inner Join jp.jobTitle jt Where jp.isActive=:isActive")
	List<JobPostingWithEmployerAndJobTitleDto> getJobPostingWithEmployerAndJobTitleDtoByIsActive(boolean isActive, Sort sort);

	@Query("Select new io.kodlama.hrms.entities.dtos.JobPostingWithEmployerAndJobTitleDto"
			+ "(jp.id, e.companyName, jt.title, jp.numberOfOpenPositions, jp.postingDate, jp.closingDate, jp.isActive)"
			+ "From JobPosting jp Inner Join jp.employer e Inner Join jp.jobTitle jt Where jp.isActive=:isActive and e.companyName=:companyName")
	List<JobPostingWithEmployerAndJobTitleDto> getJobPostingWithEmployerAndJobTitleDtoByIsActiveAndCompanyName(boolean isActive, String companyName);

}
