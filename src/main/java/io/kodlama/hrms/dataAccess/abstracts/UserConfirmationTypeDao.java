package io.kodlama.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import io.kodlama.hrms.entities.concretes.UserConfirmationType;

public interface UserConfirmationTypeDao extends JpaRepository<UserConfirmationType, Integer> {

}
