package io.kodlama.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import io.kodlama.hrms.entities.concretes.CompanyStaff;

public interface CompanyStaffDao extends JpaRepository<CompanyStaff, Integer> {

}
