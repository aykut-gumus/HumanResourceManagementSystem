package com.hrms.hrms.business.abstracts;

import java.util.List;

import com.hrms.hrms.core.utilities.result.DataResult;
import com.hrms.hrms.core.utilities.result.Result;
import com.hrms.hrms.entities.concretes.Employer;

public interface EmployerService {

	DataResult<List<Employer>> getAll();

	Result add(Employer employer);

	DataResult<Employer> getByEmail(String email);
}
