package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.entities.User;
import io.kodlama.hrms.core.utilities.results.DataResult;

public interface UserService {

	DataResult<List<User>> getAll();

	DataResult<User> getById(int id);

	DataResult<User> getByEmail(String email);

}
