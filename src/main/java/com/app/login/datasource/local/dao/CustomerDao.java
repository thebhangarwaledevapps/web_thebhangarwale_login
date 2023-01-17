package com.app.login.datasource.local.dao;

import com.app.login.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerDao extends JpaRepository<Customer, String> {

    Optional<Customer> findByMobileId(String id);
}
