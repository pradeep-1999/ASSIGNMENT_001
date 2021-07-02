package com.example.demo.entities.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Users;

public interface UserRepository extends CrudRepository<Users, Integer> {
	
	@Query(value="SELECT * FROM users ORDER BY marks DESC",nativeQuery=true)
	 List<Users> getLeaderBoard();
	
	Users findByEmail(String email);
	Users findByUserId(String userId);
	
}
