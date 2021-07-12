package io.kodlama.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.WorkingTimeService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.WorkingTimeDao;
import io.kodlama.hrms.entities.concretes.WorkingTime;

@Service
public class WorkingTimeManager implements WorkingTimeService {
	
	private WorkingTimeDao workingTimeDao;

	@Autowired
	public WorkingTimeManager(WorkingTimeDao workingTimeDao) {
		this.workingTimeDao = workingTimeDao;
	}

	@Override
	public Result add(WorkingTime workingTime) {
		
		workingTimeDao.save(workingTime);
		return new SuccessResult();
	}

	@Override
	public Result update(WorkingTime workingTime) {
		
		workingTimeDao.save(workingTime);
		return new SuccessResult();
	}

	@Override
	public Result delete(WorkingTime workingTime) {
		
		workingTimeDao.delete(workingTime);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<WorkingTime>> getAll() {
		return new SuccessDataResult<List<WorkingTime>>(workingTimeDao.findAll());
	}

	@Override
	public DataResult<WorkingTime> getById(int id) {
		return new SuccessDataResult<WorkingTime>(workingTimeDao.getById(id));
	}

}
