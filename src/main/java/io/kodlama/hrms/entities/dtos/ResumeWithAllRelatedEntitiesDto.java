package io.kodlama.hrms.entities.dtos;

import java.time.LocalDateTime;
import java.util.List;

import io.kodlama.hrms.entities.concretes.Candidate;
import io.kodlama.hrms.entities.concretes.CoverLetter;
import io.kodlama.hrms.entities.concretes.Education;
import io.kodlama.hrms.entities.concretes.Experience;
import io.kodlama.hrms.entities.concretes.Image;
import io.kodlama.hrms.entities.concretes.LanguageLevel;
import io.kodlama.hrms.entities.concretes.Link;
import io.kodlama.hrms.entities.concretes.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeWithAllRelatedEntitiesDto {

	private int id;

	private LocalDateTime creationDate;

	private Candidate candidate;

	private CoverLetter coverLetter;

	private Image image;

	private List<Education> educations;

	private List<Experience> experiences;

	private List<LanguageLevel> languageLevels;

	private List<Link> links;

	private List<Skill> skills;

}
