package io.kodlama.hrms.business.abstracts;

import java.util.List;

import io.kodlama.hrms.entities.concretes.JobTitle;

public interface JobTitleService {

	List<JobTitle> getAll();

}
