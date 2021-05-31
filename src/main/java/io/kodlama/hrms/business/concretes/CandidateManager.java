package io.kodlama.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.CandidateService;
import io.kodlama.hrms.business.abstracts.UserActivationService;
import io.kodlama.hrms.business.adapters.UserCheckService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.ErrorResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.CandidateDao;
import io.kodlama.hrms.entities.concretes.Candidate;
import io.kodlama.hrms.entities.concretes.UserActivation;

@Service
public class CandidateManager implements CandidateService {

	private CandidateDao candidateDao;
	private UserCheckService userCheckService;
	private UserActivationService userActivationService;

	@Autowired
	public CandidateManager(CandidateDao candidateDao, UserCheckService userCheckService, UserActivationService userActivationService) {
		this.candidateDao = candidateDao;
		this.userCheckService = userCheckService;
		this.userActivationService = userActivationService;
	}

	@Override
	public Result add(Candidate candidate) {

		if (checkIfNullField(candidate)) {
			return new ErrorResult("Lütfen boş alanları doldurunuz.");
		}

		if (!userCheckService.checkIfRealPerson(candidate.getIdentityNumber(), candidate.getFirstName(),
				candidate.getLastName(), candidate.getYearOfBirth())) {
			return new ErrorResult("Lütfen bilgilerinizi doğru giriniz.");
		}

		if (checkIfIdentityNumberExists(candidate.getIdentityNumber())) {
			return new ErrorResult("Girilen kimlik numarası başka bir hesaba aittir.");
		}
		
		candidateDao.save(candidate);
		userActivationService.add(new UserActivation(candidate));
		return new SuccessResult("Aktivasyon kodu gönderildi.");
	}

	@Override
	public Result update(Candidate candidate) {
		candidateDao.save(candidate);
		return new SuccessResult();
	}

	@Override
	public Result delete(Candidate candidate) {
		candidateDao.delete(candidate);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(candidateDao.findAll());
	}

	@Override
	public DataResult<Candidate> getById(int id) {
		return new SuccessDataResult<Candidate>(candidateDao.getById(id));
	}
	
	@Override
	public Result activate(UserActivation userActivation) {
		
		userActivation.setActivated(true);
		userActivation.setIsActivatedDate(LocalDate.now());
		
		userActivationService.update(userActivation);
		return new SuccessResult("Üyelik işlemleri tamamlanmıştır.");
	}

	private boolean checkIfNullField(Candidate candidate) {

		boolean result = false;

		if (candidate.getEmail() == null || candidate.getPassword() == null || candidate.getFirstName() == null
				|| candidate.getLastName() == null || candidate.getIdentityNumber() == null
				|| candidate.getYearOfBirth() == 0) {
			result = true;
		}

		return result;
	}

	private boolean checkIfIdentityNumberExists(String identityNumber) {

		boolean result = false;

		for (Candidate candidate : getAll().getData()) {
			if (candidate.getIdentityNumber() == identityNumber) {
				result = true;
			}
		}

		return result;
	}

}
