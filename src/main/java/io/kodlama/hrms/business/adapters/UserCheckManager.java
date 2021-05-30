package io.kodlama.hrms.business.adapters;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class UserCheckManager implements UserCheckService {

	@Override
	public boolean checkIfRealPerson(String identityNumber, String firstName, String lastName, LocalDate dateOfBirth) {
		return false;
	}

}
