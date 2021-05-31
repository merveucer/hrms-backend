package io.kodlama.hrms.business.concretes;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.EmailService;
import io.kodlama.hrms.business.abstracts.UserActivationService;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.UserActivationDao;
import io.kodlama.hrms.entities.concretes.UserActivation;

@Service
public class UserActivationManager implements UserActivationService {

	private UserActivationDao userActivationDao;
	private EmailService emailService;

	@Autowired
	public UserActivationManager(UserActivationDao userActivationDao, EmailService emailService) {
		this.userActivationDao = userActivationDao;
		this.emailService = emailService;
	}

	@Override
	public Result add(UserActivation userActivation) {
		
		userActivation.setCode(generateCode());
		userActivation.setActivated(false);
		userActivation.setIsActivatedDate(LocalDate.now());
		
		userActivationDao.save(userActivation);
		return emailService.sendEmail(userActivation.getUser());
	}
	
	@Override
	public Result update(UserActivation userActivation) {
		
		userActivationDao.save(userActivation);
		return new SuccessResult();
	}

	private String generateCode() {

		UUID code = UUID.randomUUID();

		return code.toString();
	}

}
