package com.app.agri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.agri.entity.PaymentMode;
@Repository
public interface PaymentModeRepository extends JpaRepository<PaymentMode, Integer> {

}
