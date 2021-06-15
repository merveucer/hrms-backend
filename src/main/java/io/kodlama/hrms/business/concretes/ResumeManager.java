package io.kodlama.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.kodlama.hrms.business.abstracts.CoverLetterService;
import io.kodlama.hrms.business.abstracts.EducationService;
import io.kodlama.hrms.business.abstracts.ExperienceService;
import io.kodlama.hrms.business.abstracts.ImageService;
import io.kodlama.hrms.business.abstracts.ResumeService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.core.utilities.results.SuccessDataResult;
import io.kodlama.hrms.core.utilities.results.SuccessResult;
import io.kodlama.hrms.dataAccess.abstracts.ResumeDao;
import io.kodlama.hrms.entities.concretes.Resume;
import io.kodlama.hrms.entities.dtos.ResumeWithAllRelatedEntitiesDto;

@Service
public class ResumeManager implements ResumeService {

	private ResumeDao resumeDao;
	private CoverLetterService coverLetterService;
	private ImageService imageService;
	private EducationService educationService;
	private ExperienceService experienceService;

	@Autowired
	public ResumeManager(ResumeDao resumeDao, CoverLetterService coverLetterService, ImageService imageService, EducationService educationService, ExperienceService experienceService) {
		this.resumeDao = resumeDao;
		this.coverLetterService = coverLetterService;
		this.imageService = imageService;
		this.educationService = educationService;
		this.experienceService = experienceService;
	}

	@Override
	public Result add(Resume resume) {

		resume.setCreationDate(LocalDate.now());

		resumeDao.save(resume);
		return new SuccessResult("Özgeçmiş eklendi.");
	}

	@Override
	public Result update(Resume resume) {
		
		resumeDao.save(resume);
		return new SuccessResult("Özgeçmiş güncellendi.");
	}

	@Override
	public Result delete(Resume resume) {
		
		resumeDao.delete(resume);
		return new SuccessResult("Özgeçmiş silindi.");
	}

	@Override
	public DataResult<List<Resume>> getAll() {
		return new SuccessDataResult<List<Resume>>(resumeDao.findAll());
	}

	@Override
	public DataResult<Resume> getById(int id) {
		return new SuccessDataResult<Resume>(resumeDao.getById(id));
	}

	@Override
	public Result addCoverLetterToResume(int resumeId, int coverLetterId) {

		Resume resume = getById(resumeId).getData();
		resume.setCoverLetter(coverLetterService.getById(coverLetterId).getData());

		update(resume);
		return new SuccessResult("Ön yazı özgeçmişe eklendi.");
	}

	@Override
	public DataResult<Resume> getByCandidateId(int candidateId) {
		return new SuccessDataResult<Resume>(resumeDao.getByCandidate_Id(candidateId));
	}

	@Override
	public DataResult<ResumeWithAllRelatedEntitiesDto> getResumeDetailsByCandidateId(int candidateId) {

		Resume resume = getByCandidateId(candidateId).getData();

		ResumeWithAllRelatedEntitiesDto resumeWithAllRelatedEntitiesDto = new ResumeWithAllRelatedEntitiesDto();
		resumeWithAllRelatedEntitiesDto.setId(resume.getId());
		resumeWithAllRelatedEntitiesDto.setCreationDate(resume.getCreationDate());
		resumeWithAllRelatedEntitiesDto.setCandidate(resume.getCandidate());
		resumeWithAllRelatedEntitiesDto.setCoverLetter(resume.getCoverLetter());
		resumeWithAllRelatedEntitiesDto.setImage(imageService.getByUserId(candidateId).getData());
		resumeWithAllRelatedEntitiesDto.setEducations(educationService.getAllByResumeIdSortedByGraduationDate(resume.getId()).getData());
		resumeWithAllRelatedEntitiesDto.setExperiences(experienceService.getAllByResumeIdSortedByTerminationDate(resume.getId()).getData());
		resumeWithAllRelatedEntitiesDto.setLanguageLevels(resume.getLanguageLevels());
		resumeWithAllRelatedEntitiesDto.setLinks(resume.getLinks());
		resumeWithAllRelatedEntitiesDto.setSkills(resume.getSkills());

		return new SuccessDataResult<ResumeWithAllRelatedEntitiesDto>(resumeWithAllRelatedEntitiesDto);
	}

}
