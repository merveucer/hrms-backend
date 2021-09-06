package io.kodlama.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.EmailService;
import io.kodlama.hrms.business.abstracts.UserActivationService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
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

		userActivation.setActivated(false);
		userActivation.setCode(generateCode());
		userActivation.setIsActivatedDate(LocalDateTime.now());

		userActivationDao.save(userActivation);
		return emailService.sendEmail(userActivation.getUser());
	}

	@Override
	public Result update(UserActivation userActivation) {

		userActivationDao.save(userActivation);
		return new SuccessResult();
	}

	@Override
	public Result delete(int id) {

		userActivationDao.deleteById(id);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<UserActivation>> getAll() {
		return new SuccessDataResult<List<UserActivation>>(userActivationDao.findAll());
	}

	@Override
	public DataResult<UserActivation> getById(int id) {
		return new SuccessDataResult<UserActivation>(userActivationDao.getById(id));
	}

	@Override
	public DataResult<UserActivation> getByCode(String code) {
		return new SuccessDataResult<UserActivation>(userActivationDao.getByCode(code));
	}

	@Override
	public DataResult<UserActivation> getByUserId(int userId) {
		return new SuccessDataResult<UserActivation>(userActivationDao.getByUser_Id(userId));
	}
	
	@Override
	public DataResult<List<UserActivation>> getAllByIsActivated(boolean isActivated) {
		return new SuccessDataResult<List<UserActivation>>(userActivationDao.getByIsActivated(isActivated));
	}


	private String generateCode() {

		UUID code = UUID.randomUUID();

		return code.toString();
	}

}
