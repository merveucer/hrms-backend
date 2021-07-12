package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kodlama.hrms.business.abstracts.CoverLetterService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.CoverLetter;

@RestController
@RequestMapping("/api/coverLetters")
public class CoverLettersController {

	private CoverLetterService coverLetterService;

	@Autowired
	public CoverLettersController(CoverLetterService coverLetterService) {
		this.coverLetterService = coverLetterService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CoverLetter coverLetter) {
		return coverLetterService.add(coverLetter);
	}

	@PostMapping("/update")
	public Result update(@RequestBody CoverLetter coverLetter) {
		return coverLetterService.update(coverLetter);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody CoverLetter coverLetter) {
		return coverLetterService.delete(coverLetter);
	}

	@GetMapping("/getAll")
	public DataResult<List<CoverLetter>> getAll() {
		return coverLetterService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<CoverLetter> getById(@RequestParam int id) {
		return coverLetterService.getById(id);
	}
	
	@GetMapping("/getByCandidateId")
	public DataResult<CoverLetter> getByCandidateId(@RequestParam int candidateId) {
		return coverLetterService.getByCandidateId(candidateId);
	}

}
