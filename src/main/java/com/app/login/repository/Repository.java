package com.app.login.repository;

import com.app.login.exception.InvalidOTPException;
import com.app.login.result.Result;
import org.springframework.stereotype.Service;

@Service
public interface Repository {

    String generateTokenAndAvailForSeveralSeconds(String countryCode, String mobileNumber);

    String generateCustomerIdAfterVerifyingOTP(String otpToken, String otp, String firebaseToken)
            throws InvalidOTPException;

    Boolean validatedCustomerId(String customerId);
}