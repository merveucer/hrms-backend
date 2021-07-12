package io.kodlama.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.WorkingTypeService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.WorkingTypeDao;
import io.kodlama.hrms.entities.concretes.WorkingType;

@Service
public class WorkingTypeManager implements WorkingTypeService {
	
	private WorkingTypeDao workingTypeDao;
	
	@Autowired
	public WorkingTypeManager(WorkingTypeDao workingTypeDao) {
		this.workingTypeDao = workingTypeDao;
	}

	@Override
	public Result add(WorkingType workingType) {
		
		workingTypeDao.save(workingType);
		return new SuccessResult();
	}

	@Override
	public Result update(WorkingType workingType) {
		
		workingTypeDao.save(workingType);
		return new SuccessResult();
	}

	@Override
	public Result delete(WorkingType workingType) {
		
		workingTypeDao.delete(workingType);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<WorkingType>> getAll() {
		return new SuccessDataResult<List<WorkingType>>(workingTypeDao.findAll());
	}

	@Override
	public DataResult<WorkingType> getById(int id) {
		return new SuccessDataResult<WorkingType>(workingTypeDao.getById(id));
	}

}
