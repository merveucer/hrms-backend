package io.kodlama.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.kodlama.hrms.entities.concretes.CoverLetter;

public interface CoverLetterDao extends JpaRepository<CoverLetter, Integer> {

	List<CoverLetter> getByCandidate_Id(int candidateId);

}
