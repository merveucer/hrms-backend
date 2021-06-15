package io.kodlama.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import io.kodlama.hrms.entities.concretes.Education;

public interface EducationDao extends JpaRepository<Education, Integer> {

	List<Education> getByResume_Id(int resumeId);

	List<Education> getByResume_Id(int resumeId, Sort sort);

}
