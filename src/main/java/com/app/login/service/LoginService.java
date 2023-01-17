package com.app.login.service;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Pattern;
import com.app.login.result.Result;
import com.app.login.result.ServerError;
import com.app.login.result.Success;
import com.app.login.util.internalization.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.login.exception.InvalidCountryCodeException;
import com.app.login.exception.InvalidMobileNumberException;
import com.app.login.exception.InvalidOTPException;
import com.app.login.repository.Repository;
import com.app.login.result.ClientError;

@Service
public class LoginService {

    @Autowired private Repository repositoryImpl;
    @Autowired private Translator translator;
    private static final String INDIAN = "91";
    private static final String COUNTRY_CODE = "^\\d{2}$";
    private static final String MOBILE_NUMBER = "^\\d{10}$";
    private static final String OTP = "^\\d{6}$";

    public Result<String> validatedCountryCodeAndMobileNumber(String countryCode, String mobileNumber) {
        Result result = null;
        try {
            if (countryCode == null) {
                throw new InvalidCountryCodeException(
                        translator.toLocale("error_valid_country_code")
                );
            }
            if (!Pattern.compile(COUNTRY_CODE).matcher(countryCode.trim()).matches()) {
                throw new InvalidCountryCodeException(
                        translator.toLocale("error_valid_country_code")
                );
            }
            if (!countryCode.trim().equals(INDIAN)) {
                throw new InvalidCountryCodeException(
                        translator.toLocale("error_valid_country_code")
                );
            }
            if (mobileNumber == null) {
                throw new InvalidMobileNumberException(
                        translator.toLocale("error_valid_mobile_number")
                );
            }
            if (!Pattern.compile(MOBILE_NUMBER).matcher(mobileNumber.trim()).matches()) {
                throw new InvalidMobileNumberException(
                        translator.toLocale("error_valid_mobile_number")
                );
            }
            result = Optional.ofNullable(repositoryImpl.generateTokenAndAvailForSeveralSeconds(
                        countryCode,
                        mobileNumber
                    ))
                    .map((Function<String, Result<String>>) Success::new)
                    .orElse(new ServerError(new Exception(
                            translator.toLocale("error_something_went_wrong")
                    )));
        } catch (InvalidCountryCodeException | InvalidMobileNumberException e) {
            result = new ClientError(e);
        }
        return result;
    }

    public Result<String> validatedOtp(String otpToken, String otp, String firebaseToken) {
        Result result = null;
        try {
            if (otp == null) {
                throw new InvalidOTPException(
                        translator.toLocale("error_valid_otp")
                );
            }
            if (!Pattern.compile(OTP).matcher(otp.trim()).matches()) {
                throw new InvalidOTPException(
                        translator.toLocale("error_valid_otp")
                );
            }
            result = Optional.ofNullable(repositoryImpl.generateCustomerIdAfterVerifyingOTP(
                        otpToken,
                        otp,
                        firebaseToken
                    ))
                    .map((Function<String, Result<String>>) Success::new)
                    .orElse(new ServerError(new InvalidOTPException(
                            translator.toLocale("error_valid_otp")
                    )));
        } catch (InvalidOTPException e) {
            result = new ClientError(e);
        }
        return result;
    }

    public boolean validatedCustomerId(String customerId) {
        return repositoryImpl.validatedCustomerId(customerId);
    }
}
