package com.app.agri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.agri.entity.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

List<Address> findByUser_UserId(int userId);

@Query(value = "select * from address where user_id=:userId", nativeQuery=true)
List<Address> findByUserId(int userId);

}
