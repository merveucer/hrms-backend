package io.kodlama.hrms.business.concretes;

import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.EmailService;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.entities.concretes.User;

@Service
public class EmailManager implements EmailService {

	@Override
	public Result sendEmail(User user) {
		
		return new SuccessResult(user.getEmail() + " adresine e-posta gönderildi.");
	}

}
