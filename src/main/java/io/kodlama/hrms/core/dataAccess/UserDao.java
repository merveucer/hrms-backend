package io.kodlama.hrms.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import io.kodlama.hrms.core.entities.User;

public interface UserDao extends JpaRepository<User, Integer> {

	User getByEmail(String email);

}
