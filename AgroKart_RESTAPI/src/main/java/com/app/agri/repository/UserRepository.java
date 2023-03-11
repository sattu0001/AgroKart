package com.app.agri.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.agri.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> 
{ 
	User findByUserEmail(String email);

	@Query("select u from User u where u.status=:status")
	List<User> findUnApprovedRequests(Boolean status);

	

}
