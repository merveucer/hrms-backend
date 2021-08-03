package io.kodlama.hrms.entities.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "educations")
public class Education {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "name_of_educational_institution")
	private String nameOfEducationalInstitution;

	@Column(name = "department")
	private String department;

	@Column(name = "degree")
	private String degree;

	@Column(name = "starting_date")
	private LocalDate startingDate;

	@Column(name = "graduation_date")
	private LocalDate graduationDate;

	@ManyToOne
	@JoinColumn(name = "resume_id")
	private Resume resume;

	public String getGraduationDate() {

		if (graduationDate == null) {
			return "Devam ediyor.";
		}

		return graduationDate.toString();
	}

}
