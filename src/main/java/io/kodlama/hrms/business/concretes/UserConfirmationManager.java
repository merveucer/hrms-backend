package io.kodlama.hrms.business.concretes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.EmailService;
import io.kodlama.hrms.business.abstracts.UserConfirmationService;
import io.kodlama.hrms.core.utilities.results.ErrorResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.UserConfirmationDao;
import io.kodlama.hrms.entities.concretes.UserConfirmation;

@Service
public class UserConfirmationManager implements UserConfirmationService {
	
	private UserConfirmationDao userConfirmationDao;
	private EmailService emailService;
	
	@Autowired
	public UserConfirmationManager(UserConfirmationDao userConfirmationDao, EmailService emailService) {
		this.userConfirmationDao = userConfirmationDao;
		this.emailService = emailService;
	}

	@Override
	public Result add(UserConfirmation userConfirmation) {
		
		userConfirmation.setIsConfirmedDate(LocalDate.now());
		
		if (userConfirmation.isConfirmed() == false) {
			
			userConfirmation.setConfirmed(false);
			
			userConfirmationDao.save(userConfirmation);
			emailService.sendEmail(userConfirmation.getUser());
			return new ErrorResult("Üyelik onaylanmadı.");
		}
		
		userConfirmation.setConfirmed(true);
		
		userConfirmationDao.save(userConfirmation);
		emailService.sendEmail(userConfirmation.getUser());
		return new SuccessResult("Üyelik onaylandı.");	
	}

}
