package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.CompanyStaff;

public interface CompanyStaffService {
	
	Result add(CompanyStaff companyStaff);
	
	Result update(CompanyStaff companyStaff);
	
	Result delete(CompanyStaff companyStaff);

	DataResult<List<CompanyStaff>> getAll();
	
	DataResult<CompanyStaff> getById(int id);

}
