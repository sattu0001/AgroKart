package com.app.agri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.agri.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	List<Order> findByUser_UserId(int userId);

	List<Order> findByUser_UserIdAndCancelOrder(int userId, Boolean false1);

}
