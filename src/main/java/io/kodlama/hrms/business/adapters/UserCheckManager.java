package io.kodlama.hrms.business.adapters;

import org.springframework.stereotype.Service;

@Service
public class UserCheckManager implements UserCheckService {

	@Override
	public boolean checkIfRealPerson(String identityNumber, String firstName, String lastName, int yearOfBirth) {
		return true;
	}

}
