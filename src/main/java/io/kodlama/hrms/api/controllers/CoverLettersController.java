package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@CrossOrigin
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

	@PutMapping("/update")
	public Result update(@RequestBody CoverLetter coverLetter) {
		return coverLetterService.update(coverLetter);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestParam int id) {
		return coverLetterService.delete(id);
	}

	@GetMapping("/getAll")
	public DataResult<List<CoverLetter>> getAll() {
		return coverLetterService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<CoverLetter> getById(@RequestParam int id) {
		return coverLetterService.getById(id);
	}

	@GetMapping("/getAllByCandidateId")
	public DataResult<List<CoverLetter>> getAllByCandidateId(@RequestParam int candidateId) {
		return coverLetterService.getAllByCandidateId(candidateId);
	}

}
