package io.kodlama.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.CandidateService;
import io.kodlama.hrms.business.abstracts.UserActivationService;
import io.kodlama.hrms.business.adapters.mernis.UserCheckService;
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

		if (!userCheckService.checkIfRealPerson(candidate.getIdentityNumber(), candidate.getFirstName(), candidate.getLastName(), candidate.getDateOfBirth())) {
			return new ErrorResult("Lütfen bilgilerinizi doğru giriniz.");
		}

		if (!checkIfIdentityNumberExists(candidate.getIdentityNumber())) {
			return new ErrorResult("Girilen kimlik numarası başka bir hesaba aittir.");
		}

		candidate.setActivated(false);

		candidateDao.save(candidate);
		return userActivationService.add(new UserActivation(candidate));
	}

	@Override
	public Result update(Candidate candidate) {

		candidateDao.save(candidate);
		return new SuccessResult("İş arayan güncellendi.");
	}

	@Override
	public Result delete(Candidate candidate) {

		candidateDao.delete(candidate);
		return new SuccessResult("İş arayan silindi.");
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
	public Result activate(String code) {

		UserActivation userActivation = userActivationService.getByCode(code).getData();

		if (userActivation == null) {
			return new ErrorResult("Geçersiz bir kod girdiniz.");
		}

		Candidate candidate = getById(userActivation.getUser().getId()).getData();

		candidate.setActivated(true);
		userActivation.setIsActivatedDate(LocalDate.now());

		update(candidate);
		userActivationService.update(userActivation);
		return new SuccessResult("Üyelik işlemleri tamamlanmıştır.");
	}

	@Override
	public DataResult<Candidate> getByIdentityNumber(String identityNumber) {
		return new SuccessDataResult<Candidate>(candidateDao.getByIdentityNumber(identityNumber));
	}

	private boolean checkIfIdentityNumberExists(String identityNumber) {

		boolean result = false;

		if (getByIdentityNumber(identityNumber).getData() == null) {
			result = true;
		}

		return result;
	}

}
