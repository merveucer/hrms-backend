package io.kodlama.hrms.entities.concretes;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "job_postings")
public class JobPosting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "job_description")
	private String jobDescription;

	@Column(name = "number_of_open_positions")
	private int numberOfOpenPositions;

	@Column(name = "salary_min")
	private String salaryMin;

	@Column(name = "salary_max")
	private String salaryMax;

	@Column(name = "posting_date")
	private LocalDateTime postingDate;

	@Column(name = "closing_date")
	private LocalDate closingDate;

	@Column(name = "is_active")
	private boolean isActive;

	@ManyToOne()
	@JoinColumn(name = "employer_id")
	private Employer employer;

	@ManyToOne()
	@JoinColumn(name = "job_title_id")
	private JobTitle jobTitle;

	@ManyToOne()
	@JoinColumn(name = "city_id")
	private City city;

	@ManyToOne()
	@JoinColumn(name = "working_time_id")
	private WorkingTime workingTime;

	@ManyToOne()
	@JoinColumn(name = "working_type_id")
	private WorkingType workingType;
	
	@JsonIgnore
	@OneToOne(mappedBy = "jobPosting")
	private JobPostingConfirmation jobPostingConfirmation;

}
