package io.kodlama.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.JobTitleService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.ErrorResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.JobTitleDao;
import io.kodlama.hrms.entities.concretes.JobTitle;

@Service
public class JobTitleManager implements JobTitleService {

	private JobTitleDao jobTitleDao;

	@Autowired
	public JobTitleManager(JobTitleDao jobTitleDao) {
		this.jobTitleDao = jobTitleDao;
	}

	@Override
	public Result add(JobTitle jobTitle) {
		
		if(!checkIfJobTitleExists(jobTitle.getTitle())) {
			this.jobTitleDao.save(jobTitle);
			return new SuccessResult("İş pozisyonu eklendi.");
		}
		
		return new ErrorResult("Eklemek istediğiniz iş pozisyonu zaten mevcut.");		
	}

	@Override
	public Result update(JobTitle jobTitle) {
		this.jobTitleDao.save(jobTitle);
		return new SuccessResult("İş pozisyonu güncellendi.");
	}

	@Override
	public Result delete(JobTitle jobTitle) {
		this.jobTitleDao.delete(jobTitle);
		return new SuccessResult("İş pozisyonu silindi.");
	}
	
	@Override
	public DataResult<List<JobTitle>> getAll() {
		return new SuccessDataResult<List<JobTitle>>(this.jobTitleDao.findAll());
	}

	@Override
	public DataResult<JobTitle> getById(int id) {
		return new SuccessDataResult<JobTitle>(this.jobTitleDao.getById(id));
	}
	
	public boolean checkIfJobTitleExists(String title) {
		
		boolean result = false;
		
		for (JobTitle jobTitle : getAll().getData()) {
			if (jobTitle.getTitle() == title) {
				result = true;
			}
		}
		
		return result;
	}

}
