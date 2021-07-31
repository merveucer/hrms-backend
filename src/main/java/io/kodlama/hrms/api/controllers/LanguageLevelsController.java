package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kodlama.hrms.business.abstracts.LanguageLevelService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.LanguageLevel;

@RestController
@RequestMapping("/api/languageLevels")
@CrossOrigin
public class LanguageLevelsController {

	private LanguageLevelService languageLevelService;

	@Autowired
	public LanguageLevelsController(LanguageLevelService languageLevelService) {
		this.languageLevelService = languageLevelService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody LanguageLevel languageLevel) {
		return languageLevelService.add(languageLevel);
	}

	@PostMapping("/update")
	public Result update(@RequestBody LanguageLevel languageLevel) {
		return languageLevelService.update(languageLevel);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody LanguageLevel languageLevel) {
		return languageLevelService.delete(languageLevel);
	}

	@GetMapping("/getAll")
	public DataResult<List<LanguageLevel>> getAll() {
		return languageLevelService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<LanguageLevel> getById(@RequestParam int id) {
		return languageLevelService.getById(id);
	}

}
