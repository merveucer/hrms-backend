package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kodlama.hrms.business.abstracts.LevelService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.Level;

@RestController
@RequestMapping("/api/levels")
public class LevelsController {
	
	private LevelService levelService;

	@Autowired
	public LevelsController(LevelService levelService) {
		this.levelService = levelService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Level level) {
		return levelService.add(level);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody Level level) {
		return levelService.update(level);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody Level level) {
		return levelService.delete(level);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<Level>> getAll() {
		return levelService.getAll();
	}
	
	@GetMapping("/getById")
	public DataResult<Level> getById(@RequestParam int id) {
		return levelService.getById(id);
	}

}
