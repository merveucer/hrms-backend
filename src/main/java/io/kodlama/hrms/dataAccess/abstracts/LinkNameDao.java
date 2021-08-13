package io.kodlama.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import io.kodlama.hrms.entities.concretes.LinkName;

public interface LinkNameDao extends JpaRepository<LinkName, Integer> {

}
