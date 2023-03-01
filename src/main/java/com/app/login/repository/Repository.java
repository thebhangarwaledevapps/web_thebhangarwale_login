package com.app.login.repository;

import com.app.login.exception.InvalidOTPException;
import com.app.login.result.Result;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface Repository {

    String generateTokenAndAvailForSeveralSeconds(String countryCode, String mobileNumber);

    Optional<String> generateCustomerIdAfterVerifyingOTP(String otpToken, String otp, String firebaseToken);

    Boolean validatedCustomerId(String customerId);
}