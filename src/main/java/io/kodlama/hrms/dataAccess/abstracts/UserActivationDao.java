package io.kodlama.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import io.kodlama.hrms.core.entities.User;
import io.kodlama.hrms.entities.concretes.UserActivation;

public interface UserActivationDao extends JpaRepository<UserActivation, Integer> {
	
	UserActivation getByCode(String code);
	
	UserActivation getByUser(User user);

}
