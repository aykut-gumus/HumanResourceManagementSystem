package com.hrms.hrms.business.concretes;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.hrms.business.abstracts.CandidateService;
import com.hrms.hrms.core.adapter.CheckMernisService;
import com.hrms.hrms.core.utilities.result.DataResult;
import com.hrms.hrms.core.utilities.result.ErrorResult;
import com.hrms.hrms.core.utilities.result.Result;
import com.hrms.hrms.core.utilities.result.SuccessDataResult;
import com.hrms.hrms.core.utilities.result.SuccessResult;
import com.hrms.hrms.dataAccess.abstracts.CandidateDao;
import com.hrms.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService {
	private CheckMernisService checkMernisService;
	private CandidateDao candidateDao;

	@Autowired
	public CandidateManager(CandidateDao candidateDao, CheckMernisService checkMernisService) {
		super();
		this.candidateDao = candidateDao;
		this.checkMernisService = checkMernisService;
	}

	private boolean validationForCandidate(Candidate candidate) {
		if (Objects.isNull(candidate.getIdentityNumber()) || Objects.isNull(candidate.getFirstName())
				|| Objects.isNull(candidate.getLastName()) || Objects.isNull(candidate.getEmail())
				|| Objects.isNull(candidate.getPassword()) || Objects.isNull(candidate.getBirthDate())) {
			return false;
		}

		return true;
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll());
	}

	@Override
	public DataResult<Candidate> getByEmail(String email) {
		return new SuccessDataResult<Candidate>(this.candidateDao.getByEmail(email));
	}

	@Override
	public DataResult<Candidate> getByIdentityNumber(String identityNumber) {
		return new SuccessDataResult<Candidate>(this.candidateDao.getByIdentityNumber(identityNumber));
	}

	@Override
	public Result add(Candidate candidate) {
		if (!checkMernisService.checkIfRealIdNumber(candidate)) {
			return new ErrorResult("Geçerli kişi değil");
		} else if (!validationForCandidate(candidate)) {
			return new ErrorResult("Eksik bilgi girişi, lütfen kontrol ediniz");
		}
		if (getByEmail(candidate.getEmail()).getData() != null) {
			return new ErrorResult("Bu e-posta adresi mevcut");
		}
		this.candidateDao.save(candidate);
		return new SuccessResult("Aday başarıyla eklendi");
	}

}