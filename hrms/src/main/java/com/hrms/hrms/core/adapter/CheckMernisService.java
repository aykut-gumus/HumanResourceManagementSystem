package com.hrms.hrms.core.adapter;

import com.hrms.hrms.entities.concretes.Candidate;

public interface CheckMernisService {
	boolean checkIfRealIdNumber(Candidate candidate);
}
