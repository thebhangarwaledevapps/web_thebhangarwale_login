package com.app.login.datasource.local.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.login.entity.Otp;

public interface OtpDao extends JpaRepository<Otp, String> {

	boolean existsByTokenAndOtpCode(String token, int otp);

	boolean existsByToken(String token);
}
