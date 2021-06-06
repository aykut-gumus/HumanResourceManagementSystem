package com.hrms.hrms.business.abstracts;

import java.util.List;

import com.hrms.hrms.core.utilities.result.DataResult;
import com.hrms.hrms.entities.concretes.User;

public interface UserService {

	DataResult<List<User>> getAll();

	User add(User user);
}
