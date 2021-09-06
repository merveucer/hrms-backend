package io.kodlama.hrms.business.concretes;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.CompanyStaffService;
import io.kodlama.hrms.business.abstracts.EmployerService;
import io.kodlama.hrms.business.abstracts.UpdatedEmployerService;
import io.kodlama.hrms.business.abstracts.UserActivationService;
import io.kodlama.hrms.business.abstracts.UserConfirmationService;
import io.kodlama.hrms.business.abstracts.UserConfirmationTypeService;
import io.kodlama.hrms.business.abstracts.UserService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.ErrorResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.EmployerDao;
import io.kodlama.hrms.entities.concretes.CompanyStaff;
import io.kodlama.hrms.entities.concretes.Employer;
import io.kodlama.hrms.entities.concretes.UpdatedEmployer;
import io.kodlama.hrms.entities.concretes.UserActivation;
import io.kodlama.hrms.entities.concretes.UserConfirmation;
import io.kodlama.hrms.entities.concretes.UserConfirmationType;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;
	private UserService userService;
	private UserActivationService userActivationService;
	private UserConfirmationService userConfirmationService;
	private UserConfirmationTypeService userConfirmationTypeService;
	private CompanyStaffService companyStaffService;
	private UpdatedEmployerService updatedEmployerService;

	@Autowired
	public EmployerManager(EmployerDao employerDao, UserService userService, UserActivationService userActivationService,
			UserConfirmationService userConfirmationService, UserConfirmationTypeService userConfirmationTypeService, CompanyStaffService companyStaffService, UpdatedEmployerService updatedEmployerService) {
		this.employerDao = employerDao;
		this.userService = userService;
		this.userActivationService = userActivationService;
		this.userConfirmationService = userConfirmationService;
		this.userConfirmationTypeService = userConfirmationTypeService;
		this.companyStaffService = companyStaffService;
		this.updatedEmployerService = updatedEmployerService;
	}

	@Override
	public Result add(Employer employer) {

		validateEmployer(employer);

		employerDao.save(employer);
		return userActivationService.add(new UserActivation(employer));
	}

	@Override
	public Result update(Employer employer) {

		validateEmployer(employer);

		Employer employerInConfirmationProcess = getById(employer.getId()).getData();
		UpdatedEmployer updatedEmployer = updatedEmployerService.getByEmployerId(employer.getId()).getData();
		
		String email = employer.getEmail() == null ? employerInConfirmationProcess.getEmail() : employer.getEmail();
		String password = employer.getPassword() == null ? employerInConfirmationProcess.getPassword() : employer.getPassword();
		String companyName = employer.getCompanyName() == null ? employerInConfirmationProcess.getCompanyName() : employer.getCompanyName();
		String webAddress = employer.getWebAddress() == null ? employerInConfirmationProcess.getWebAddress() : employer.getWebAddress();
		String phoneNumber = employer.getPhoneNumber() == null ? employerInConfirmationProcess.getPhoneNumber() : employer.getPhoneNumber();
		
		if (updatedEmployer == null) {
			updatedEmployer = new UpdatedEmployer(0, email, password, companyName, webAddress, phoneNumber,	employer);
		} else {
			updatedEmployer.setEmail(email);
			updatedEmployer.setPassword(password);
			updatedEmployer.setCompanyName(companyName);
			updatedEmployer.setWebAddress(webAddress);
			updatedEmployer.setPhoneNumber(phoneNumber);
		}

		updatedEmployerService.add(updatedEmployer);
		return new SuccessResult("İşveren güncellemesi onay aşamasındadır.");
	}

	@Override
	public Result delete(int id) {

		employerDao.deleteById(id);
		return new SuccessResult("İşveren silindi.");
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(employerDao.findAll());
	}

	@Override
	public DataResult<Employer> getById(int id) {
		return new SuccessDataResult<Employer>(employerDao.getById(id));
	}

	@Override
	public Result activate(String code) {

		UserActivation userActivation = userActivationService.getByCode(code).getData();

		if (userActivation == null) {
			return new ErrorResult("Geçersiz bir kod girdiniz.");
		}

		userActivation.setActivated(true);
		userActivation.setIsActivatedDate(LocalDateTime.now());
		
		userActivationService.update(userActivation);
		return new SuccessResult("Üyeliğiniz onay aşamasındadır.");
	}

	@Override
	public Result confirm(int employerId, int companyStaffId, int userConfirmationTypeId, boolean isConfirmed) {

		Employer employer = getById(employerId).getData();
		CompanyStaff companyStaff = companyStaffService.getById(companyStaffId).getData();
		UserConfirmationType userConfirmationType = userConfirmationTypeService.getById(userConfirmationTypeId).getData();
		UpdatedEmployer updatedEmployer = updatedEmployerService.getByEmployerId(employerId).getData();

		if (!isConfirmed && userConfirmationTypeId == 1) {
			userActivationService.delete(userActivationService.getByUserId(employer.getId()).getData().getId());
			delete(employer.getId());
			return new ErrorResult("İşveren hesabı onaylanmadı.");
		}

		if (isConfirmed && userConfirmationTypeId == 1) {
			userConfirmationService.add(new UserConfirmation(employer, companyStaff, userConfirmationType, isConfirmed));
			return new SuccessResult("İşveren hesabı onaylandı.");
		}

		if (!isConfirmed && userConfirmationTypeId == 2) {
			userConfirmationService.add(new UserConfirmation(employer, companyStaff, userConfirmationType, isConfirmed));
			return new ErrorResult("İşveren güncellemesi onaylanmadı.");
		}

		employer.setEmail(updatedEmployer.getEmail());
		employer.setPassword(updatedEmployer.getPassword());
		employer.setCompanyName(updatedEmployer.getCompanyName());
		employer.setWebAddress(updatedEmployer.getWebAddress());
		employer.setPhoneNumber(updatedEmployer.getPhoneNumber());

		employerDao.save(employer);
		updatedEmployerService.delete(updatedEmployer.getId());
		userConfirmationService.add(new UserConfirmation(employer, companyStaff, userConfirmationType, isConfirmed));
		return new SuccessResult("İşveren güncellemesi onaylandı.");
	}
	
	@Override
	public DataResult<List<Employer>> getAllByIsActivated(boolean isActivated) {
		return new SuccessDataResult<List<Employer>>(employerDao.getByUserActivation_IsActivated(isActivated));
	}
	
	@Override
	public DataResult<List<Employer>> getAllByIsConfirmedAndUserConfirmationTypeId(boolean isConfirmed, int userConfirmationTypeId) {
		return new SuccessDataResult<List<Employer>>(employerDao.getByUserConfirmations_IsConfirmedAndUserConfirmations_UserConfirmationType_Id(isConfirmed, userConfirmationTypeId));
	}

	private boolean checkIfEmailExists(String email) {

		boolean result = false;

		if (userService.getByEmail(email).getData() == null) {
			result = true;
		}

		return result;
	}

	private boolean checkIfDomainsMatch(String webAddress, String email) {

		String[] splitEmailArray = email.split("@");

		return webAddress.contains(splitEmailArray[1]);
	}

	private Result validateEmployer(Employer employer) {
		
		if (employer.getEmail() == null || employer.getPassword() == null || employer.getCompanyName() == null || employer.getWebAddress() == null || employer.getPhoneNumber() == null ) {
			return null;
		}

		if (!checkIfEmailExists(employer.getEmail())) {
			return new ErrorResult("Girilen e-posta adresi başka bir hesaba aittir.");
		}

		if (!checkIfDomainsMatch(employer.getWebAddress(), employer.getEmail())) {
			return new ErrorResult("Web adresi ile e-posta aynı alan adına sahip olmalıdır.");
		}

		return null;
	}	

}
