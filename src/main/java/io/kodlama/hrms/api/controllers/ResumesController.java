package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kodlama.hrms.business.abstracts.ResumeService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.Resume;
import io.kodlama.hrms.entities.dtos.ResumeWithAllRelatedEntitiesDto;

@RestController
@RequestMapping("/api/resumes")
public class ResumesController {

	private ResumeService resumeService;

	@Autowired
	public ResumesController(ResumeService resumeService) {
		this.resumeService = resumeService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody Resume resume) {
		return resumeService.add(resume);
	}

	@PostMapping("/update")
	public Result update(@RequestBody Resume resume) {
		return resumeService.update(resume);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody Resume resume) {
		return resumeService.delete(resume);
	}

	@GetMapping("/getAll")
	public DataResult<List<Resume>> getAll() {
		return resumeService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<Resume> getById(@RequestParam int id) {
		return resumeService.getById(id);
	}

	@PostMapping("/addCoverLetterToResume")
	public Result addCoverLetterToResume(@RequestParam int resumeId, @RequestParam int coverLetterId) {
		return resumeService.addCoverLetterToResume(resumeId, coverLetterId);
	}

	@GetMapping("/getResumeDetailsByCandidateId")
	public DataResult<ResumeWithAllRelatedEntitiesDto> getResumeDetailsByCandidateId(@RequestParam int candidateId) {
		return resumeService.getResumeDetailsByCandidateId(candidateId);
	}

}
