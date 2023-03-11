package com.app.agri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.agri.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByProductName(String productName);

	List<Product> findByCategory_CategoryIdAndUser_UserIdAndIsActive(int categoryId, int userId, Boolean true1);

	List<Product> findByUser_UserIdAndIsActive(int userId, Boolean true1);

	List<Product> findByCategory_CategoryIdAndIsActive(int categoryId,Boolean isActive);

	List<Product> findByIsActive(Boolean true1);

}
