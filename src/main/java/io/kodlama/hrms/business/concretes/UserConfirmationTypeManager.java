package io.kodlama.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.UserConfirmationTypeService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.UserConfirmationTypeDao;
import io.kodlama.hrms.entities.concretes.UserConfirmationType;

@Service
public class UserConfirmationTypeManager implements UserConfirmationTypeService{
	
	private UserConfirmationTypeDao userConfirmationTypeDao;
	
	@Autowired
	public UserConfirmationTypeManager(UserConfirmationTypeDao userConfirmationTypeDao) {
		this.userConfirmationTypeDao = userConfirmationTypeDao;
	}

	@Override
	public Result add(UserConfirmationType userConfirmationType) {
		
		userConfirmationTypeDao.save(userConfirmationType);
		return new SuccessResult("Onay tipi eklendi.");
	}

	@Override
	public Result update(UserConfirmationType userConfirmationType) {
		
		userConfirmationTypeDao.save(userConfirmationType);
		return new SuccessResult("Onay tipi güncellendi.");
	}

	@Override
	public Result delete(int id) {
		
		userConfirmationTypeDao.deleteById(id);
		return new SuccessResult("Onay tipi silindi.");
	}

	@Override
	public DataResult<List<UserConfirmationType>> getAll() {
		return new SuccessDataResult<List<UserConfirmationType>>(userConfirmationTypeDao.findAll());
	}

	@Override
	public DataResult<UserConfirmationType> getById(int id) {
		return new SuccessDataResult<UserConfirmationType>(userConfirmationTypeDao.getById(id));
	}

}
