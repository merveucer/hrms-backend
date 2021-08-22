package io.kodlama.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.UpdatedEmployerService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.UpdatedEmployerDao;
import io.kodlama.hrms.entities.concretes.UpdatedEmployer;

@Service
public class UpdatedEmployerManager implements UpdatedEmployerService {

	private UpdatedEmployerDao updatedEmployerDao;

	@Autowired
	public UpdatedEmployerManager(UpdatedEmployerDao updatedEmployerDao) {
		this.updatedEmployerDao = updatedEmployerDao;
	}

	@Override
	public Result add(UpdatedEmployer updatedEmployer) {

		updatedEmployerDao.save(updatedEmployer);
		return new SuccessResult("Güncellenmiş işveren eklendi.");
	}

	@Override
	public Result update(UpdatedEmployer updatedEmployer) {

		updatedEmployerDao.save(updatedEmployer);
		return new SuccessResult("Güncellenmiş işveren güncellendi.");
	}

	@Override
	public Result delete(int id) {

		updatedEmployerDao.deleteById(id);
		return new SuccessResult("Güncellenmiş işveren silindi.");
	}

	@Override
	public DataResult<List<UpdatedEmployer>> getAll() {
		return new SuccessDataResult<List<UpdatedEmployer>>(updatedEmployerDao.findAll());
	}

	@Override
	public DataResult<UpdatedEmployer> getById(int id) {
		return new SuccessDataResult<UpdatedEmployer>(updatedEmployerDao.getById(id));
	}

	@Override
	public DataResult<UpdatedEmployer> getByEmployerId(int employerId) {
		return new SuccessDataResult<UpdatedEmployer>(updatedEmployerDao.getByEmployer_Id(employerId));
	}

}
