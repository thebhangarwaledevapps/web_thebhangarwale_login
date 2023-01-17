package com.app.login.datasource.local.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.login.entity.Mobile;

public interface MobileDao extends JpaRepository<Mobile, String>{

	Optional<Mobile> findByMobileNumber(String mobileNumber);

	Optional<Mobile> findByOtpToken(String otpToken);

}
