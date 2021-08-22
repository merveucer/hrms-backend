package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kodlama.hrms.business.abstracts.LinkNameService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.LinkName;

@RestController
@RequestMapping("/api/linkNames")
@CrossOrigin
public class LinkNamesController {

	private LinkNameService linkNameService;

	@Autowired
	public LinkNamesController(LinkNameService linkNameService) {
		this.linkNameService = linkNameService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody LinkName linkName) {
		return linkNameService.add(linkName);
	}

	@PutMapping("/update")
	public Result update(@RequestBody LinkName linkName) {
		return linkNameService.update(linkName);
	}

	@GetMapping("/getAll")
	public DataResult<List<LinkName>> getAll() {
		return linkNameService.getAll();
	}

	@GetMapping("getById")
	public DataResult<LinkName> getById(@RequestParam int id) {
		return linkNameService.getById(id);
	}

}
