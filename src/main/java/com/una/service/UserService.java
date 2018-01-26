package com.una.service;

import com.una.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public User findUserById(Long id);

}
