package com.app.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.login.result.ClientError;
import com.app.login.result.Result;
import com.app.login.result.ServerError;
import com.app.login.result.Success;
import com.app.login.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired private LoginService loginService;

    @GetMapping("/validatedCountryCodeAndMobileNumber")
    public ResponseEntity validatedCountryCodeAndMobileNumber(
            @RequestParam String countryCode,
            @RequestParam String mobileNumber
    ) {
        return getResultResponseEntity(loginService.validatedCountryCodeAndMobileNumber(countryCode, mobileNumber));
    }

    @GetMapping("/validatedOtp")
    public ResponseEntity validatedOtp(
            @RequestParam String otpToken,
            @RequestParam String otp,
            @RequestParam String firebaseToken
    ) {
        return getResultResponseEntity(loginService.validatedOtp(otpToken, otp, firebaseToken));
    }

    @GetMapping("/validatedCustomerId")
    public ResponseEntity validatedCustomerId(
            @RequestParam String customerId
    ) {
        return ResponseEntity.ok(loginService.validatedCustomerId(customerId));
    }

    private ResponseEntity getResultResponseEntity(Result result) {
        ResponseEntity responseEntity = null;
        if (result instanceof Success) {
            Success success = (Success) result;
            responseEntity = ResponseEntity.ok(success.getData());
        } else if (result instanceof ClientError) {
            ClientError clientError = (ClientError) result;
            responseEntity = ResponseEntity.badRequest().body(clientError.getException().getMessage());
        } else if (result instanceof ServerError) {
            ServerError serverError = (ServerError) result;
            responseEntity = ResponseEntity.internalServerError().body(serverError.getException().getMessage());
        }
        return responseEntity;
    }

}
 