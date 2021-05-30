package io.kodlama.hrms.business.adapters;

import java.time.LocalDate;

public interface UserCheckService {
	
	boolean checkIfRealPerson(String identityNumber, String firstName, String lastName, LocalDate dateOfBirth);

}
