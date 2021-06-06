package com.hrms.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.hrms.business.abstracts.JobTitleService;
import com.hrms.hrms.entities.concretes.JobTitle;

@RestController
@RequestMapping("api/jobtitles")

public class JobTitleController {

	private JobTitleService jobTitlesService;

	@Autowired
	public JobTitleController(JobTitleService jobTitlesService) {
		super();
		this.jobTitlesService = jobTitlesService;
	}

	@GetMapping("getall")
	public List<JobTitle> getAll() {
		return jobTitlesService.getAll();
	}
}
