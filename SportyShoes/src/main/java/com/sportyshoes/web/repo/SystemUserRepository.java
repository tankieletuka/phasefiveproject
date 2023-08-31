package com.sportyshoes.web.repo;


import org.springframework.data.repository.CrudRepository;
import com.sportyshoes.web.entity.SystemUser;

public interface SystemUserRepository extends CrudRepository<SystemUser	, String>{
	SystemUser findByEmail(String email);
	boolean existsByEmail(String email);
	Iterable<SystemUser> findBySurnameLikeIgnoreCase(String name);
	Iterable<SystemUser> findByNameLikeIgnoreCaseOrSurnameLikeIgnoreCaseOrEmailLikeIgnoreCase(String name, String surname, String email);
}
