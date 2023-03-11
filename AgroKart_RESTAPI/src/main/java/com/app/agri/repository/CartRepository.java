package com.app.agri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.agri.entity.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	List<Cart> findByUser_UserId(int userId);

	void deleteByUser_UserId(int userId);

}
