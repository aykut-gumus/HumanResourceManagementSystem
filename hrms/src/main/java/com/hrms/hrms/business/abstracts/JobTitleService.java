package com.hrms.hrms.business.abstracts;

import java.util.List;

import com.hrms.hrms.entities.concretes.JobTitle;

public interface JobTitleService {

	List<JobTitle> getAll();
}
