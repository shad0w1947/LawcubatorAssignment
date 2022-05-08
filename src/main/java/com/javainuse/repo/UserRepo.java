package com.javainuse.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javainuse.model.DAOUser;

@Repository
public interface UserRepo extends CrudRepository<DAOUser, Long> {

	DAOUser findByUsername(String username);
	
}