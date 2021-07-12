package io.kodlama.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import io.kodlama.hrms.entities.concretes.Level;

public interface LevelDao extends JpaRepository<Level, Integer>{

}
