package io.kodlama.hrms.business.adapters.mernis;

import java.rmi.RemoteException;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisServiceAdapter /*implements UserCheckService*/ {

	/*@Override*/
	public boolean checkIfRealPerson(String identityNumber, String firstName, String lastName, LocalDate dateOfBirth) {

		KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();

		boolean result = false;

		try {
			result = kpsPublicSoapProxy.TCKimlikNoDogrula(
					Long.parseLong(identityNumber),
					firstName.toUpperCase(),
					lastName.toUpperCase(),
					dateOfBirth.getYear()
					);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return result;
	}

}
