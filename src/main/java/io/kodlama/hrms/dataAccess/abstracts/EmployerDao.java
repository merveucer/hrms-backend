package io.kodlama.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.kodlama.hrms.entities.concretes.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer>{
	
	List<Employer> getByIsActivatedAndIsConfirmed(boolean isActivated, boolean isConfirmed);
	
}
