package io.kodlama.hrms.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "jobPostings"})
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
@Table(name = "employers")
public class Employer extends User {
	
	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "web_address")
	private String webAddress;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "is_activated")
	private boolean isActivated;
	
	@Column(name = "is_confirmed")
	private boolean isConfirmed;
	
	@OneToMany(mappedBy = "employer")
	private List<JobPosting> jobPostings;

}
