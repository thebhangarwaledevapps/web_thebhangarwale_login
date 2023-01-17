package com.app.login.datasource.local.dao;

import com.app.login.entity.FirebaseToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirebaseTokenDao extends JpaRepository<FirebaseToken,String> {
}
