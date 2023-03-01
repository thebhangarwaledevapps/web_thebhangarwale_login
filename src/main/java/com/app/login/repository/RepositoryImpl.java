package com.app.login.repository;

import com.app.login.datasource.environment.EnvironmentDataSource;
import com.app.login.datasource.local.*;
import com.app.login.datasource.network.NetworkDataSource;
import com.app.login.entity.Customer;
import com.app.login.entity.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.login.entity.Mobile;
import com.app.login.entity.Otp;

import static java.util.Arrays.asList;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class RepositoryImpl implements Repository {

    @Autowired
    private DatabaseDataSource databaseDataSourceImpl;

    @Autowired
    private EnvironmentDataSource environmentDataSourceImpl;

    @Autowired
    private NetworkDataSource networkDataSourceImpl;

    @Override
    public String generateTokenAndAvailForSeveralSeconds(String countryCode, String mobileNumber) {
        return databaseDataSourceImpl.getMobileByMobileNumber(mobileNumber).map(mobile -> {
            int otpCode = environmentDataSourceImpl.getOtp();
            sendOTP(countryCode, mobileNumber, otpCode);
            mobile.setOtp(new Otp(otpCode));
            deleteOtpAfterOneMin(mobile);
            return databaseDataSourceImpl.saveMobileAndFetchToken(mobile);
        }).orElseGet(() -> {
            final int otpCode = environmentDataSourceImpl.getOtp();
            sendOTP(countryCode, mobileNumber, otpCode);
            final Mobile mobile = new Mobile(mobileNumber, countryCode, new Otp(otpCode));
            deleteOtpAfterOneMin(mobile);
            return databaseDataSourceImpl.saveMobileAndFetchToken(mobile);
        });
    }

    @Override
    public Optional<String> generateCustomerIdAfterVerifyingOTP(String otpToken, String otpCode, String firebaseToken) {
        return Optional.ofNullable(
                databaseDataSourceImpl
                        .isTokenAndOtpCodeAvailable(otpToken, Integer.parseInt(otpCode))
                        ? true
                        : null
        ).flatMap(isTokenAndOtpCodeAvailable -> databaseDataSourceImpl
                .getMobileByOtpToken(otpToken)
                .map(mobile -> {
                    mobile.setOtp(null);
                    return databaseDataSourceImpl.saveMobile(mobile);
                })
                .map(mobile -> databaseDataSourceImpl
                        .getCustomerByMobileId(mobile.getId())
                        .map(customer -> {
                            if (!databaseDataSourceImpl.isFirebaseTokenAvailable(firebaseToken)) {
                                customer.getFirebaseTokens()
                                        .add(new FirebaseToken(firebaseToken));
                            }
                            return databaseDataSourceImpl.saveCustomerAndFetchId(customer);
                        })
                        .orElse(databaseDataSourceImpl
                                .saveCustomerAndFetchId(new Customer(
                                        asList(new FirebaseToken(firebaseToken)),
                                        mobile
                                )))
                ));
    }

    @Override
    public Boolean validatedCustomerId(String customerId) {
        return databaseDataSourceImpl.isCustomerExist(customerId);
    }

    private void sendOTP(String countryCode, String mobileNumber, int otpCode) {
        networkDataSourceImpl.sendOtp(
                environmentDataSourceImpl.getSmsApiKey(),
                environmentDataSourceImpl.getSmsSenderId(),
                environmentDataSourceImpl.getSmsChannel(),
                environmentDataSourceImpl.getSmsDcs(),
                environmentDataSourceImpl.getSmsFlashSms(),
                environmentDataSourceImpl.getSmsRoute(),
                countryCode.concat(mobileNumber),
                environmentDataSourceImpl.getSmsContent()
                        .concat(String.valueOf(otpCode))
                        .concat(" ")
                        .concat(environmentDataSourceImpl.getSmsAuth())
        );
    }

    private void deleteOtpAfterOneMin(final Mobile mobile) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                databaseDataSourceImpl.getMobileByMobileNumber(mobile.getMobileNumber()).ifPresent(mobile -> {
                    mobile.setOtp(null);
                    databaseDataSourceImpl.saveMobile(mobile);
                });
            }
        }, Integer.parseInt(environmentDataSourceImpl.getDelay()));
    }

}
