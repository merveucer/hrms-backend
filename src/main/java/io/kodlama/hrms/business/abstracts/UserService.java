package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.entities.concretes.User;

public interface UserService {
	
	DataResult<List<User>> getAll();
	
	DataResult<User> getById(int id);

}
