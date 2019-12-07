package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value="SELECT * FROM USERS LIMIT ?,?", nativeQuery = true)
	List<User> findUserWithPage(int start, int length);
	
	@Query(value="SELECT count(*) FROM USERS", nativeQuery = true)
	int totalUsers();
	
}
