package com.sportyshoes.web.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.web.entity.LoginEntity;
import com.sportyshoes.web.entity.SystemUser;
import com.sportyshoes.web.repo.SystemUserRepository;

@Service
@Transactional
public class SystemUserService {

	@Autowired
	private SystemUserRepository userRepo;

	// Add a user
	public SystemUser addUser(SystemUser user) throws Exception {
		if (this.userRepo.existsByEmail(user.getEmail()))
			throw new Exception(user.getEmail() + " already exists.");
		return this.userRepo.save(user);
	}

	public SystemUser updateUser(SystemUser user) throws Exception {
		if (this.userRepo.existsByEmail(user.getEmail()))
			return this.userRepo.save(user);
		throw new Exception(user.getEmail() + "does not exist.");
	}

	// Delete a user
	public void deleteUser(String email) throws Exception {
		if (!this.userRepo.existsByEmail(email))
			throw new Exception(email + " does not exist and cannot delete");
		this.userRepo.deleteById(email);
	}

	// Get all users
	public List<SystemUser> getAllUsers() {
		List<SystemUser> users = new ArrayList<>();

		this.userRepo.findAll().forEach(user -> {
			SystemUser data = new SystemUser(user.getId(), user.getEmail(), user.getName(), user.getSurname(),
					user.isIsadmin());
			users.add(data);
		});

		return users;
	}
	
	// Get all users like a given surname
		public List<SystemUser> getAllUsersBySurname(String surname) {
			List<SystemUser> users = new ArrayList<>();

			this.userRepo.findBySurnameLikeIgnoreCase(surname).forEach(user -> {
				SystemUser data = new SystemUser(user.getId(), user.getEmail(), user.getName(), user.getSurname(),
						user.isIsadmin());
				users.add(data);
			});

			return users;
		}
		
		public List<SystemUser> getAllUsersByUserDetails(String searchValue) {
			List<SystemUser> users = new ArrayList<>();

			this.userRepo.findByNameLikeIgnoreCaseOrSurnameLikeIgnoreCaseOrEmailLikeIgnoreCase(searchValue,searchValue,searchValue).forEach(user -> {
				SystemUser data = new SystemUser(user.getId(), user.getEmail(), user.getName(), user.getSurname(),
						user.isIsadmin());
				users.add(data);
			});

			return users;
		}

	// Get a user by email
	public SystemUser getUserByEmail(String email) {
		return this.userRepo.findByEmail(email);
		// .orElseThrow(()-> new EntityNotFoundException(email + " not found."));
	}

	// Validate user credentials for log in into the system
	public boolean validateUser(LoginEntity loginEntity) {

		SystemUser user = getUserByEmail(loginEntity.getEmail());

		if (user != null) {
			if (user.isIsadmin()) {
				return loginEntity.getPassword().equals(user.getPassword());
			}
		}

		return false;
	}
}
