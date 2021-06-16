package io.kodlama.hrms.business.abstracts;

import io.kodlama.hrms.core.entities.User;
import io.kodlama.hrms.core.utilities.results.Result;

public interface EmailService {

	Result sendEmail(User user);

}
