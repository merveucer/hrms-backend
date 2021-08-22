package io.kodlama.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import io.kodlama.hrms.entities.concretes.Resume;

public interface ResumeDao extends JpaRepository<Resume, Integer> {

	Resume getByCandidate_Id(int candidateId);
	
	Resume getByCoverLetter_Id(int coverLetterId);

}
