package com.hrms.hrms.business.abstracts;

import java.util.List;

import com.hrms.hrms.core.utilities.result.DataResult;
import com.hrms.hrms.core.utilities.result.Result;
import com.hrms.hrms.entities.concretes.Candidate;

public interface CandidateService {

	DataResult<List<Candidate>> getAll();

	Result add(Candidate candidate);

	DataResult<Candidate> getByEmail(String email);

	DataResult<Candidate> getByIdentityNumber(String identityNumber);
}
