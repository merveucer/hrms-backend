package io.kodlama.hrms.business.adapters;

public interface UserCheckService {
	
	boolean checkIfRealPerson(String identityNumber, String firstName, String lastName, int yearOfBirth);

}
