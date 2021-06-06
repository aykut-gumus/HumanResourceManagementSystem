package com.hrms.hrms.business.concretes;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.hrms.business.abstracts.EmployerService;
import com.hrms.hrms.core.utilities.result.DataResult;
import com.hrms.hrms.core.utilities.result.ErrorResult;
import com.hrms.hrms.core.utilities.result.Result;
import com.hrms.hrms.core.utilities.result.SuccessDataResult;
import com.hrms.hrms.core.utilities.result.SuccessResult;
import com.hrms.hrms.dataAccess.abstracts.EmployerDao;
import com.hrms.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;

	@Autowired
	public EmployerManager(EmployerDao employerDao) {
		super();
		this.employerDao = employerDao;
	}

	private boolean validationForEmployer(Employer employer) {
		if (Objects.isNull(employer.getCompanyName()) || Objects.isNull(employer.getWebAddress())
				|| Objects.isNull(employer.getEmail()) || Objects.isNull(employer.getPhoneNumber())
				|| Objects.isNull(employer.getPassword())) {
			return false;
		}

		return true;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll());
	}

	@Override
	public DataResult<Employer> getByEmail(String email) {
		return new SuccessDataResult<Employer>(this.employerDao.getByEmail(email));
	}

	@Override
	public Result add(Employer employer) {
		if (getByEmail(employer.getEmail()).getData() != null) {
			return new ErrorResult("Bu e-posta adresi mevcut");
		}
		if (!this.validationForEmployer(employer)) {
			return new ErrorResult("Eksik bilgi girişi, lütfen kontrol ediniz");
		}
		this.employerDao.save(employer);
		return new SuccessResult("Şirket başarıyla eklendi");

	}

}
