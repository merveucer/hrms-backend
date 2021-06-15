package io.kodlama.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.kodlama.hrms.business.abstracts.LinkService;
import io.kodlama.hrms.core.utilities.results.DataResult;
import io.kodlama.hrms.core.utilities.results.Result;
import io.kodlama.hrms.entities.concretes.Link;

@RestController
@RequestMapping("/api/links")
public class LinksController {

	private LinkService linkService;

	@Autowired
	public LinksController(LinkService linkService) {
		this.linkService = linkService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody Link link) {
		return linkService.add(link);
	}

	@PostMapping("/update")
	public Result update(@RequestBody Link link) {
		return linkService.update(link);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody Link link) {
		return linkService.delete(link);
	}

	@GetMapping("/getAll")
	public DataResult<List<Link>> getAll() {
		return linkService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<Link> getById(@RequestParam int id) {
		return linkService.getById(id);
	}

}
