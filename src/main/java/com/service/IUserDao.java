package com.service;

import com.model.Login;

public interface IUserDao {
	public Login query(Login login);
	public int searchIdByUsername(String username);
	public int searchLastId();
}
