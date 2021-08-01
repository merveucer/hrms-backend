package io.kodlama.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import io.kodlama.hrms.entities.concretes.UpdatedEmployer;

public interface UpdatedEmployerDao extends JpaRepository<UpdatedEmployer, Integer> {

	UpdatedEmployer getByEmployer_Id(int employerId);

}
