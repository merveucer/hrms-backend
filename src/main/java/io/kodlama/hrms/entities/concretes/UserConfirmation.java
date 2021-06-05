package io.kodlama.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "user_confirmations")
public class UserConfirmation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "is_confirmed_date")
	private LocalDate isConfirmedDate;
	
	@OneToOne()
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne()
	@JoinColumn(name = "company_staff_id")
	private CompanyStaff companyStaff;
	
	public UserConfirmation(User user, CompanyStaff companyStaff){
		this.setUser(user);
		this.setCompanyStaff(companyStaff);
	}

}
