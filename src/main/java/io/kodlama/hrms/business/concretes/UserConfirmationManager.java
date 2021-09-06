package io.kodlama.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.EmailService;
import io.kodlama.hrms.business.abstracts.UserConfirmationService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
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

		userConfirmation.setIsConfirmedDate(LocalDateTime.now());

		userConfirmationDao.save(userConfirmation);
		emailService.sendEmail(userConfirmation.getUser());
		return new SuccessResult();
	}

	@Override
	public Result update(UserConfirmation userConfirmation) {

		userConfirmationDao.save(userConfirmation);
		return new SuccessResult();
	}

	@Override
	public Result delete(int id) {

		userConfirmationDao.deleteById(id);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<UserConfirmation>> getAll() {
		return new SuccessDataResult<List<UserConfirmation>>(userConfirmationDao.findAll());
	}

	@Override
	public DataResult<UserConfirmation> getById(int id) {
		return new SuccessDataResult<UserConfirmation>(userConfirmationDao.getById(id));
	}

	@Override
	public DataResult<List<UserConfirmation>> getAllByUserId(int userId) {
		return new SuccessDataResult<List<UserConfirmation>>(userConfirmationDao.getByUser_Id(userId));
	}
	
	@Override
	public DataResult<List<UserConfirmation>> getAllByIsConfirmedAndUserConfirmationTypeId(boolean isConfirmed, int userConfirmationTypeId) {
		return new SuccessDataResult<List<UserConfirmation>>(userConfirmationDao.getByIsConfirmedAndUserConfirmationType_Id(isConfirmed, userConfirmationTypeId));
	}

}
